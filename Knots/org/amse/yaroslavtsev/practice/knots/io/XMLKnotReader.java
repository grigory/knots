package org.amse.yaroslavtsev.practice.knots.io;

import java.util.*;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;

import org.amse.yaroslavtsev.practice.knots.model.*;

public class XMLKnotReader implements XMLKnotProcessor {
	
	private HashMap <Integer,IPoint> myPoints = new HashMap<Integer,IPoint>();
	private HashMap <Integer,IEdge> myEdges = new HashMap<Integer,IEdge>();	
	private Node myRoot;
	private NodeList myBlocks;
	private int myBlock = 0;
	private StringBuffer myErrorMessages = new StringBuffer();
	private boolean myErrors = false;
	private boolean myErrorsOccurred;

	private int getInt(String s) {
		int i = Integer.parseInt(s.trim());
		return i;
	}
	
	private void addError(String message) {
		myErrors = true;
		myErrorMessages.append(message + "\n");
	}

	private Node getBlockRoot(String name) {
		Node blockRoot;
		String blockName;
		do {
			blockRoot = myBlocks.item(myBlock++);
			if (blockRoot == null) {
				break;
			}
			blockName = blockRoot.getNodeName();
			if (blockName != name && blockName != "#text" && blockName != null) {
				addError("Unexpected tag " + blockName + " found");
			}
		} while (blockName != name);
		if (blockRoot == null) {
			addError("Tag <" + name + "> was not found on its place");
		}
		return blockRoot;
	}
	
	private int readPointCoordinate(String name, NamedNodeMap attributes) {
		myErrorsOccurred = false;
		Node point = attributes.getNamedItem(name);
		if (point == null) {
			addError("Coordinate " + name + " not specified");
			myErrorsOccurred = true;
			return 0;
		}
		String pointString = point.getNodeValue();
		int pointNumber;
		try {
			pointNumber = Integer.parseInt(pointString.trim());
		} catch (NumberFormatException e) {
			addError("Invalid " + name + " coordinate found: " + pointString);
			myErrorsOccurred = true;
			return 0;
		}
		return pointNumber;
	
	}

	private void readPoints(IKnot model) {
		Node root = getBlockRoot(XMLKnotProcessor.POINTS);
		if (root == null) {
			return;
		}
		NodeList elements = root.getChildNodes();
		int length = elements.getLength();
		for (int i = 0; i < length; i++) {
			Node currentNode = elements.item(i); 			
			String nodeName = currentNode.getNodeName();
			if (nodeName == "#text") {
				continue;
			}		
			if (nodeName != XMLKnotProcessor.POINT) {
				addError("Unexpected tag " + nodeName + " in block <points> found");
				continue;
			}
			NamedNodeMap attributes = currentNode.getAttributes();
			
			int id = readElementID(XMLKnotProcessor.POINT, attributes);
			if (myPoints.get(id) != null) {
				addError("Duplicate " + XMLKnotProcessor.POINT + " ID found: " +  
				attributes.getNamedItem(XMLKnotProcessor.ID).getNodeValue());
				myErrorsOccurred = true;
			}
			if (myErrorsOccurred) {
				continue;
			}
			
			int x = readPointCoordinate(XMLKnotProcessor.X, attributes);
			if (myErrorsOccurred) {
				continue;
			}
			
			int y = readPointCoordinate(XMLKnotProcessor.Y, attributes);
			if (myErrorsOccurred) {
				continue;
			}
			IPoint point = model.addPoint(x, y);
			myPoints.put(id, point);		    	
		}
	}

	private int readElementID(String name, NamedNodeMap attributes) {
		myErrorsOccurred = false;
		Node id = attributes.getNamedItem(XMLKnotProcessor.ID);
		if (id == null) {
			addError("ID for " + name + " not specified");
			myErrorsOccurred = true;
			return 0;
		}
		String idString = id.getNodeValue();
		int idNumber;
		try {
			idNumber = Integer.parseInt(idString.trim());
		} catch (NumberFormatException e) {
			addError("Invalid " + name + " ID found: " + idString);
			myErrorsOccurred = true;
			return 0;
		}
		return idNumber;
	}
	
	private int readEdgePoint(String name, NamedNodeMap attributes, HashMap <Integer, IPoint> points) {
		myErrorsOccurred = false;
		Node point = attributes.getNamedItem(name);
		if (point == null) {
			addError("Edge " + name + " not specified");
			myErrorsOccurred = true;
			return 0;
		}
		String pointString = point.getNodeValue();
		int pointNumber;
		try {
			pointNumber = Integer.parseInt(pointString.trim());
			if (points.get(pointNumber) == null) {
				addError("ID of " + name + " point " + pointNumber + " was not listed in points");
				myErrorsOccurred = true;
				return 0;
			}
		} catch (NumberFormatException e) {
			addError("Invalid " + name + " point ID found: " + pointString);
			myErrorsOccurred = true;
			return 0;
		}
		return pointNumber;
	}

	private void readEdges(IKnot model) {
		List <IPoint> sources = new ArrayList <IPoint> ();
		List <IPoint> targets = new ArrayList <IPoint> ();
		Node root = getBlockRoot(XMLKnotProcessor.EDGES);
		if (root == null) {
			return;
		}
		NodeList elements = root.getChildNodes();
		int length = elements.getLength();
		for (int i = 0; i < length; i++) {
			Node currentNode = elements.item(i); 			
			String nodeName = currentNode.getNodeName();
			if (nodeName == "#text") {
				continue;
			}		
			if (nodeName != XMLKnotProcessor.EDGE) {
				addError("Unexpected tag " + nodeName + " in block <edges> found");
				continue;
			}
			NamedNodeMap attributes = currentNode.getAttributes();
			
			int id = readElementID(XMLKnotProcessor.EDGE, attributes);
			if (myEdges.get(id) != null) {
				addError("Duplicate " + XMLKnotProcessor.EDGE + " ID found: " +  
				attributes.getNamedItem(XMLKnotProcessor.ID).getNodeValue());
				myErrorsOccurred = true;
			}
			if (myErrorsOccurred) {
				continue;
			}
			
			int source = readEdgePoint(XMLKnotProcessor.SOURCE, attributes, myPoints);
			if (myErrorsOccurred) {
				continue;
			}
			
			int target  = readEdgePoint(XMLKnotProcessor.TARGET, attributes, myPoints);
			if (myErrorsOccurred) {
				continue;
			}
			
			IPoint sourcePoint = myPoints.get(source);
			IPoint targetPoint = myPoints.get(target);
			sources.add(sourcePoint);
			targets.add(targetPoint);
			IEdge edge = model.addEdge(sourcePoint, targetPoint);
			if (edge == null) {
				addError("Edge can't be added to the knot");
				continue;
			}
			myEdges.put((Integer)id, edge);		
		}
		for (IEdge current : model.edges()) {
			boolean found = false;
			for (int i = 0; i < sources.size(); i++) {
				if (current.getSource() == sources.get(i) && current.getTarget() == targets.get(i)) {
					found = true;
					break;
				}
			}
			if (!found) {
				addError("Some directions in the file were wrong and had to be corrected");
				return;
			}
		}
	}

	private void findIntersection(IKnot model, boolean [] flags, IEdge upper, IEdge lower) {
		boolean found = false;
		int position = 0;
		int current = 0;
		for (IIntersection intersection : model.intersections()) {
			if (intersection.getUpper() == upper && intersection.getLower() == lower)	{
				found = true;
				position = current;
			}
			if (intersection.getUpper() == lower && intersection.getLower() == upper) {
				intersection.changeType();
				found = true;
				position = current;
			}
			current++;				
		}
		if (!found) {
			throw new KnotException("Intersection not found", false);
		}	
		if (flags[position]) {
			throw new KnotException("The same intersection found more than once", false);
		}
		flags[position] = true;
	}
	
	private int readIntersectionEdge(String name, NamedNodeMap attributes, HashMap <Integer, IEdge> edges) {
		myErrorsOccurred = false;
		Node edge = attributes.getNamedItem(name);
		if (edge == null) {
			addError("Intersection's " + name + " edge not specified");
			myErrorsOccurred = true;
			return 0;
		}
		String edgeString = edge.getNodeValue();
		int edgeNumber;
		try {
			edgeNumber = Integer.parseInt(edgeString.trim());
			if (edges.get(edgeNumber) == null) {
				addError("ID of " + name + " edge " + edgeNumber + " was not listed in edges");
				myErrorsOccurred = true;
				return 0;
			}
		} catch (NumberFormatException e) {
			addError("Invalid " + name + " edge ID found: " + edgeString);
			myErrorsOccurred = true;
			return 0;
		}
		return edgeNumber;
	}
	
	private void readIntersections(IKnot model) {
		int size = model.intersections().size();
		boolean [] flags = new boolean[size];
		for (int i = 0; i < size; i++) {
			flags[i] = false;
		}
		Node root = getBlockRoot(XMLKnotProcessor.INTERSECTIONS);
		if (root == null) {
			return;
		}
		NodeList elements = root.getChildNodes();
		int length = elements.getLength();
		for (int i = 0; i < length; i++) {
			Node currentNode = elements.item(i); 			
			String nodeName = currentNode.getNodeName();
			if (nodeName == "#text") {
				continue;
			}		
			if (nodeName != XMLKnotProcessor.INTERSECTION) {
				addError("Unexpected tag " + nodeName + " in block <intersections> found");
				continue;
			}
			NamedNodeMap attributes = currentNode.getAttributes();
			
			int upper  = readIntersectionEdge(XMLKnotProcessor.UPPER, attributes, myEdges);
			if (myErrorsOccurred) {
				continue;
			}
			int lower  = readIntersectionEdge(XMLKnotProcessor.LOWER, attributes, myEdges);
			if (myErrorsOccurred) {
				continue;
			}
			IEdge upperEdge = myEdges.get(upper);
			IEdge lowerEdge = myEdges.get(lower);
			findIntersection(model, flags, upperEdge, lowerEdge);			
		}
		for (int i = 0; i < size; i++) {
			if (!flags[i]) {
				throw new KnotException("Not all intersections found", false);
			}
		}
	}
	
	public void process(IKnot model, String fileName) {
		Document document = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = builder.parse(fileName);
		} catch (ParserConfigurationException e) {
			throw new KnotException("Parser configuration failure", true);
		} catch (SAXException e) {
			throw new KnotException("Syntax errors occurred in the file", true);
		} catch (IOException e) {
			throw new KnotException("The file can't be read", true);
		}

		if (document != null) {
			model.clear();
			myRoot = document.getDocumentElement();
			myBlocks = myRoot.getChildNodes();
			if (myRoot.getNodeName() != XMLKnotProcessor.KNOT) {
				throw new KnotException("Tag <knot> not found", true);
			}
			readPoints(model);
		        readEdges(model);
		        readIntersections(model);
			if (myErrors) {	
				throw new KnotException(myErrorMessages.toString(), false);
			}
		}	
	}
	
//	public static void main(String args[]) {
//		XMLKnotReader reader = new XMLKnotReader();		
//		reader.process(KnotFactory.createKnot(),"KnotTest.xml");
//	}
}

