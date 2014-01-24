package org.amse.yaroslavtsev.practice.knots.io;

import java.io.*;
import java.util.*;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.math.*;

public class XMLKnotWriter implements XMLKnotProcessor {

        public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
	
	public void process(IKnot myModel, String fileName) {
		Document document = null;
	        try {
	        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder builder = factory.newDocumentBuilder();
	        	document = builder.newDocument();
	        	
	        } catch (ParserConfigurationException e) {
	        }
		Element knot = document.createElement(XMLKnotProcessor.KNOT);		
		document.appendChild(knot);
		
		List <IPoint> points = myModel.points();
		Element pointNode = document.createElement(XMLKnotProcessor.POINTS);
		knot.appendChild(pointNode);
		for (IPoint currentPoint : points) {
			Element point = document.createElement(XMLKnotProcessor.POINT);		
			pointNode.appendChild(point);
			point.setAttribute(XMLKnotProcessor.ID,"" + points.indexOf(currentPoint));
			point.setAttribute(XMLKnotProcessor.X,"" + currentPoint.getX());
			point.setAttribute(XMLKnotProcessor.Y,"" + currentPoint.getY());
		
		}
		
		List <IEdge> edges = myModel.edges();
		Element edgeNode = document.createElement(XMLKnotProcessor.EDGES);
		knot.appendChild(edgeNode);
		for (IEdge currentEdge : edges) {
			Element edge = document.createElement(XMLKnotProcessor.EDGE);
			edgeNode.appendChild(edge);
			edge.setAttribute(XMLKnotProcessor.ID,"" + edges.indexOf(currentEdge));
			edge.setAttribute(XMLKnotProcessor.SOURCE,"" + points.indexOf(currentEdge.getSource()));
			edge.setAttribute(XMLKnotProcessor.TARGET,"" + points.indexOf(currentEdge.getTarget()));
		}

		List <IIntersection> intersections = myModel.intersections();
		Element intersectionNode = document.createElement(XMLKnotProcessor.INTERSECTIONS);
		knot.appendChild(intersectionNode);
		for (IIntersection currentIntersection : intersections) {
			Element intersection = document.createElement(XMLKnotProcessor.INTERSECTION);
			intersectionNode.appendChild(intersection);
			intersection.setAttribute(XMLKnotProcessor.UPPER,"" + edges.indexOf(currentIntersection.getUpper()));
			intersection.setAttribute(XMLKnotProcessor.LOWER,"" + edges.indexOf(currentIntersection.getLower()));
		}
		try {
		        // Create a transformer
		        TransformerFactory factory  = TransformerFactory.newInstance();
			factory.setAttribute("indent-number", 4);
			Transformer xformer = factory.newTransformer();
    
		        // Set the public and system id
        		xformer.setOutputProperty(OutputKeys.INDENT, "yes");
//                        xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        		// Write the DOM document to a file
		        Source source = new DOMSource(document);
		        Result result = new StreamResult(new OutputStreamWriter(new FileOutputStream(new File(fileName)),"utf-8"));
		        xformer.transform(source, result);
			} catch (FileNotFoundException e) {
			} catch (UnsupportedEncodingException e) {
			} catch (TransformerConfigurationException e) {
		    	} catch (TransformerException e) {
		 }		

		
/*		try {
			try {
				OutputStreamWriter fout;
				fout = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("test.pkd")),"8859_1");
				try {
					KnotDiagram diagram = new KnotDiagram(myModel);
                                        fout.write(diagram.jonesPolynomial());
					fout.close();
				} catch (IOException e) {
				}
			} catch (FileNotFoundException e){
			}
		} catch (UnsupportedEncodingException e) {
		}*/
		

		
/*		
		try {
			try {
				OutputStreamWriter fout;
				fout = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(fileName)),"8859_1");
				try {
					fout.write(XML_HEADER);
					
					HashMap <IEdge,Integer> edgeids = new HashMap<IEdge,Integer>();
					List <IPoint> points = myModel.points();
					fout.write("<knot>\n");
					int pointid = 0;
					fout.write("	<points>\n");
					for (IPoint current : points) {
						fout.write("		<point id=\"" + pointid +"\" x=\"" + current.getX() + "\" y=\"" + current.getY() + "\" />\n");							
						pointids.put(current,(Integer)pointid);
						pointid++;
					}
					fout.write("	</points>\n");					
					fout.write("	<edges>\n");
					List <IEdge> edges = myModel.edges();
					int edgeid = 0;
					for (IEdge current : edges) {
						fout.write("		<edge " +
						        "id=\"" + edgeid +"\" " +
							"source=\"" + pointids.get(current.getSource()).intValue() + "\" " +
							"target=\"" + pointids.get(current.getTarget()).intValue() + "\" />\n");											
						edgeids.put(current,(Integer)edgeid);
						edgeid++;
					}
					fout.write("	</edges>\n");
					fout.write("	<intersections>\n");
					List <IIntersection> intersections = myModel.intersections();
					for (IIntersection current : intersections) {
						fout.write("		<intersection " +
							"upper=\"" + edgeids.get(current.getUpper()).intValue() + "\" " +
							"lower=\"" + edgeids.get(current.getLower()).intValue() + "\" />\n");											
					}							
					fout.write("	</intersections>\n");
					fout.write("</knot>\n");
//					fout.write(myModel.toString());
					fout.close();
				} catch (IOException e) {
					System.out.println("IOException");
				}
			} catch (FileNotFoundException e){
				System.out.println("File " + fileName + " not found!");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(
				"This VM does not support the Latin-1 character set."
			);
		}
		*/		
	}
	
//	public static void main(String args[]) {
//		XMLKnotWriter testWriter = new XMLKnotWriter();
//		testWriter.process("test.xml");
//	}
}
