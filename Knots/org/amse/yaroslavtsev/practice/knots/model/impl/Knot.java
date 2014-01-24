package org.amse.yaroslavtsev.practice.knots.model.impl;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.util.*;

class Knot implements IKnot {
	private List <IPoint> myPoints;
	private List <IEdge> myEdges;
	private List <IIntersection> myIntersections;
	private boolean myToUpdate = true;

	public Knot() {
		myPoints = new ArrayList <IPoint> ();
		myEdges = new ArrayList <IEdge> ();
		myIntersections = new ArrayList <IIntersection> ();	
	}
	
	public List <IPoint> points() {
		return Collections.unmodifiableList(myPoints);
	}

	public IPoint addPoint(int x, int y) {
		Point newPoint = new Point(x, y, this);
		myPoints.add(newPoint);	
		return newPoint;
	}

	public void removePoint(IPoint point) {
		for (Iterator <IEdge> it = myEdges.iterator(); it.hasNext();) {
			IEdge current = it.next();
		  	if (current.getSource() == point || current.getTarget() == point) {
		                removeIntersectionsWithEdge(current);
		  		it.remove();
	  		}
		}
       		
		boolean done = myPoints.remove(point);
		if (!done) {
			throw new KnotException("Point to remove was not found in the knot!");				
		}
	}

	public List <IEdge> edges() {
		return Collections.unmodifiableList(myEdges);
	}
	
	public boolean canAddEdge(IPoint source) {
		int degree = 0;
		for (IEdge current : myEdges) {
			if (current.getSource() == source) {
				degree++;
			} else if (current.getTarget() == source) {
				degree++;
			}
			if (degree >= 2) {
				return false;
			}
		}
		return true;
		//return pointDegree(source) < 2;
	}
	
	public boolean canAddEdge(IPoint source, IPoint target) {
		if (!canAddEdge(source) || !canAddEdge(target) || source == target) {
			return false;
		}
		for (IEdge current : myEdges) {
			if ((current.getSource() == source && current.getTarget() == target) ||
			    (current.getSource() == target && current.getTarget() == source)) {
			 	return false;
			}
		}
		return true;
	}

	public int pointDegree(IPoint point) {
		int degree = 0;
		
		for (IEdge current : myEdges) {
			if (current.getSource() == point) {
				degree++;
			} else if (current.getTarget() == point) {
				degree++;
			}
		}
		return degree;
	}

	private void modifyDirections(IEdge edge) {
		IEdge sourceEdge = getAnotherEdgeWithPoint(edge.getSource(),edge);		
		IEdge targetEdge = getAnotherEdgeWithPoint(edge.getTarget(),edge);
		if (sourceEdge == null) {
			if (targetEdge != null && targetEdge.getTarget() == edge.getTarget()) {
				edge.rotate();	
			}
			return;		
		}
		if (targetEdge == null) {
			if (sourceEdge.getSource() == edge.getSource()) {
				edge.rotate();
			}
			return;
		}
		if (sourceEdge.getTarget() == edge.getSource()) {
			if (targetEdge.getTarget() == edge.getTarget()) {
				for (IEdge current = targetEdge; current != null; current = getAnotherEdgeWithPoint(current.getTarget(),current)) {
					current.rotate();
				}
			}
			return;
		} 
		if (sourceEdge.getSource() == edge.getSource()) {
			if (targetEdge.getTarget() == edge.getTarget()) {
				edge.rotate();
			} else {
				for (IEdge current = sourceEdge; current != null; current = getAnotherEdgeWithPoint(current.getSource(),current)) {
					current.rotate();
				}	
			}
			return;
		}
	}
	
	
	public IEdge addEdge(IPoint source, IPoint target) {
		myToUpdate = true;
		if (!canAddEdge(source,target)) {
			return null;
		}
		Edge newEdge = new Edge(source,target);
		myEdges.add(newEdge);
		modifyDirections(newEdge);
		return newEdge;
	}

	public void removeEdge(IEdge edge) {
		removeIntersectionsWithEdge(edge);
		boolean done = myEdges.remove(edge);
		if (!done) { 
			throw new KnotException("Edge to remove was not found in the knot!");
		}
	}
	
	public List <IIntersection> intersections() {
		if (myToUpdate) {
			updateIntersections();
			myToUpdate = false;
		}
		return Collections.unmodifiableList(myIntersections);
	}
	
	public IIntersection addIntersection(IEdge upper, IEdge lower) {
	        if (upper == lower) {
	        	return null;
	        }
		for (IIntersection current : myIntersections) {
			if (current.getUpper() == upper && current.getLower() == lower) {
				return null;
			}
			if (current.getUpper() == lower && current.getLower() == upper) {
				throw new KnotException("Intersection of the other type exists in the knot!");			
			}
		}
		IIntersection newIntersection = new Intersection(upper,lower);
		myIntersections.add(newIntersection);
		return newIntersection;
	}
	
	public void removeIntersection(IIntersection intersection) {
		boolean done = myIntersections.remove(intersection);
		if (!done) {
			throw new KnotException("Intersection to remove was not found in the knot!");
		}
	}
	
	public void removeIntersection(IEdge first, IEdge second) {
		for (Iterator <IIntersection> it = myIntersections.iterator(); it.hasNext();) {
			IIntersection current = it.next();
		  	if ((current.getUpper() == first && current.getLower() == second) || (current.getLower() == first && current.getUpper() == second)) {
		  		it.remove();
	  		}
		}	
	}
	
	private void removeIntersectionsWithEdge(IEdge edge) {
		for (Iterator <IIntersection> it = myIntersections.iterator(); it.hasNext();) {
			IIntersection current = it.next();
		  	if (current.getUpper() == edge || current.getLower() == edge) {
		  		it.remove();
	  		}
		}	
	}
	
	private void updateIntersection(IEdge first, IEdge second) {
	        boolean exists = false;
		for (IIntersection intersection : myIntersections) {
			if (
				(intersection.getUpper() == first && intersection.getLower() == second) || 
				(intersection.getLower() == first && intersection.getUpper() == second)
			) {
				exists = true;
				break;				
			}
		}		
		if (!exists) {
			addIntersection(first,second);
		}
	}
	
 	private void  updateIntersections() {
		List <IEdge> edges = myEdges;
		List <IIntersection> intersections = myIntersections;
		for (IEdge first : edges) {
			for (IEdge second : edges) {
				FloatPoint2D  intersection = FloatPoint2D.getIntersectionPoint(first,second);
				if (intersection == null) {
					removeIntersection(first,second);
				} else {
					updateIntersection(first,second);
					updateIntersection(second,first);
				}            
			}
		}
	}

	private IEdge getAnotherEdgeWithPoint(IPoint point, IEdge first) {
		for (IEdge current : myEdges) {
			if ((current.getSource() == point || current.getTarget() == point) && current != first) {
				return current;
			}
		}
		return null;
	}
	
	public boolean isComplete() {
		for (IPoint current: myPoints) {
			if (canAddEdge(current)) {
				return false;
			}
		}
		return (myPoints.size() != 0);
	}
	
	public String toString() {
		return myPoints.toString() + myEdges + myIntersections;	
	}

	public void clear() {
		myPoints.clear();
		myEdges.clear();
		myIntersections.clear();
	}

	public IKnot getCopy() {
		Knot copy = KnotFactory.createKnot();
		Map <IPoint, IPoint> pointMap = new HashMap <IPoint, IPoint> ();
		Map <IEdge, IEdge> edgeMap = new HashMap <IEdge, IEdge> ();
		for (IPoint currentPoint : myPoints) {
			IPoint newPoint = new Point(currentPoint.getX(), currentPoint.getY(), copy);			
			copy.myPoints.add(newPoint);
			pointMap.put(currentPoint, newPoint);
		}
		for (IEdge currentEdge : myEdges) {
			IEdge newEdge = new Edge(pointMap.get(currentEdge.getSource()), pointMap.get(currentEdge.getTarget()));			
			copy.myEdges.add(newEdge);
			edgeMap.put(currentEdge, newEdge);
		}
		for (IIntersection currentIntersection : myIntersections) {
			IIntersection newIntersection = new Intersection(edgeMap.get(currentIntersection.getUpper()), edgeMap.get(currentIntersection.getLower()));			
			copy.myIntersections.add(newIntersection);
		}
		return copy;
	}
	
	public void setToUpdate(boolean toUpdate) {
		myToUpdate = toUpdate;
	}
}
