package org.amse.yaroslavtsev.practice.knots.math;

import java.util.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.util.*;
import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.math.Polynomial.Monomial;


public class KnotDiagram {

        private List <Intersection> myIntersections;
        private List <IIntersection> myKnotIntersections;
	private IKnot myKnot;        
	private int myColors = 0;
	private int myUsedColors = 0;
	private int myNumInt;
	private int myTrivialKnots = 0;
	private Polynomial myA = new Polynomial();
	private Polynomial myB = new Polynomial();
	private Polynomial myC = new Polynomial();
	private Polynomial myD = new Polynomial();
	private Polynomial myE = new Polynomial();
        
        private class Edge {
        	private int myColor = -1;
        	private Intersection mySource, myTarget;
		private int mySourceEdgeIndex, myTargetEdgeIndex;
		public Edge(Intersection source, Intersection target, int sourceEdgeIndex, int targetEdgeIndex) {
			mySource = source;
			myTarget = target;
			mySourceEdgeIndex = sourceEdgeIndex;
			myTargetEdgeIndex = targetEdgeIndex;
		}
	}
        
        private class Intersection {
       		private Edge[] myEdges = new Edge[4]; 	
                private int myState = 0;

        	Intersection() {
        		for (int i = 0; i < 4; i++) {
        			myEdges[i] = null;
        		}
        	}
        }

	private int numberOfIntersectionsWithEdge(IEdge edge) {
	        List <IIntersection> intersections = new ArrayList <IIntersection> ();
		int result = 0;
		for (IIntersection intersection : myKnot.intersections()) {
			if (intersection.getUpper() == edge || intersection.getLower() == edge) {
				result++;
			}
		}
		return result;
	}

	private List <IIntersection> getIntersectionsWithEdge(IEdge edge) {
	        List <IIntersection> intersections = new ArrayList <IIntersection> ();
		for (IIntersection intersection : myKnot.intersections()) {
				if (intersection.getUpper() == edge || intersection.getLower() == edge) {
					intersections.add(intersection);
			}
		}
		return intersections;
	}
	
	private IEdge getAnotherEdgeWithPoint(IPoint point, IEdge first) {
		for (IEdge current : myKnot.edges()) {
			if ((current.getSource() == point || current.getTarget() == point) && current != first) {
				return current;
			}
		}
		return null;
	}
	
	private void getColors(int color, Intersection intersection, int edgeIndex, Intersection startIntersection, int startEdgeIndex) {
		intersection.myEdges[edgeIndex].myColor = color;
		Intersection next = intersection.myEdges[edgeIndex].myTarget;
		int nextIndex; 
		if (next.myState == 0) {
			nextIndex = intersection.myEdges[edgeIndex].myTargetEdgeIndex ^ 1;
		} else {
			nextIndex = intersection.myEdges[edgeIndex].myTargetEdgeIndex ^ 2;
		}
		if (next == intersection && edgeIndex == intersection.myEdges[edgeIndex].myTargetEdgeIndex) {
			next = intersection.myEdges[edgeIndex].mySource;
			if (next.myState == 0) {
				nextIndex = intersection.myEdges[edgeIndex].mySourceEdgeIndex ^ 1;
			} else {
				nextIndex = intersection.myEdges[edgeIndex].mySourceEdgeIndex ^ 2;
			}
		}
		if (startIntersection != next || startEdgeIndex != nextIndex) {
			getColors(color, next, nextIndex, startIntersection, startEdgeIndex);
		}			
	}
	                                                                                           
	private IPoint [] getOrderedPoints(IIntersection intersection) {
		IPoint upSource = intersection.getUpper().getSource();
		IPoint upTarget = intersection.getUpper().getTarget();
	        IPoint lowSource = intersection.getLower().getSource();
	        IPoint lowTarget = intersection.getLower().getTarget();
		IntPoint2D  p1 = new IntPoint2D (upTarget.getX() - upSource.getX(), upTarget.getY() - upSource.getY());	
		IntPoint2D  p2 = new IntPoint2D (lowTarget.getX() - upSource.getX(), lowTarget.getY() - upSource.getY());
		IPoint points [] = new IPoint[4];
		if (p1.crossProduct(p2) < 0) {
//			//System.out.println("LALA");
			points[0] = lowTarget;			
			points[3] = lowSource;
		} else {
			points[3] = lowTarget;			
			points[0] = lowSource;
		
		}
		points[1] = upTarget;
		points[2] = upSource;
		return points;
	}
	
	private int getIndexByPoint(IPoint point, IIntersection intersection) {
		IPoint points [] = getOrderedPoints(intersection);	        
		for (int i = 0; i < 4; i++) {
			if (points[i] == point) {
				return i;
			}
		}
		return -1;
	}
	
	private IPoint getPointByIndex(int index, IIntersection intersection) {
		IPoint points[] = getOrderedPoints(intersection);
		return points[index];
	}
	
	private Intersection getIntersection(IIntersection intersection) {
		for (int i = 0; i < myKnotIntersections.size(); i++) {
			if (myKnotIntersections.get(i) == intersection) {
				return myIntersections.get(i);
			}
		}
		return null;
	}
	
	private void go(IEdge edge, int intIndex, int edgeIndex, boolean first) {
		FloatPoint2D start, dir;
		//System.out.println(edge);
		IIntersection intersection = myKnotIntersections.get(intIndex);
		if (first) {
			start = FloatPoint2D.getIntersectionPoint(intersection.getUpper(), intersection.getLower());							
		} else {
			start = new FloatPoint2D(edge.getSource().getX(), edge.getSource().getY());
		}		 
		dir = new FloatPoint2D(edge.getTarget().getX(), edge.getTarget().getY());
		dir.subtract(start);		
		List <IIntersection> intersections = getIntersectionsWithEdge(edge);
		boolean found = false;
		double closest = 0;
		IIntersection nextIntersection = null;
		Intersection oldIntersection = myIntersections.get(intIndex);	
		for (IIntersection current : intersections) {
			FloatPoint2D point = FloatPoint2D.getIntersectionPoint(current.getUpper(), current.getLower());
			point.subtract(start);
			double inner = point.innerProduct(dir);
			if (inner > 0 && current != oldIntersection) {
				if (found && inner < closest) {
					closest = inner;
					nextIntersection = current;
				} else if (!found) {
					found = true;
					closest = inner;
					nextIntersection = current;
				}
			}
		}
		if (found) {
			Intersection newIntersection = getIntersection(nextIntersection);
			int index = getIndexByPoint(edge.getSource(), nextIntersection);
			Edge newEdge = new Edge(oldIntersection, newIntersection, edgeIndex, index);
			oldIntersection.myEdges[edgeIndex] = newEdge;
			newIntersection.myEdges[index] = newEdge;
									
		} else {
			go(getAnotherEdgeWithPoint(edge.getTarget(),edge), intIndex, edgeIndex, false);
		}
	}
	
	private void modifyColors(long mask) {
		long newmask = mask + 1;		
		for (long i = 0 ; i < myNumInt; i++) {
			if ((mask & (1 << i)) != (newmask & (1 << i))) {
				Intersection current = myIntersections.get((int)i);
				if ((newmask & (1 << i)) != 0) {
					current.myState = 1;
					if (current.myEdges[0].myColor != current.myEdges[2].myColor) {
						getColors(current.myEdges[0].myColor, current, 1, current, 1);
						myColors--;
					} else {
						getColors(myUsedColors, current, 1, current, 1);
					        myUsedColors++;
					        myColors++;
					}
				} else {
					current.myState = 0;
					if (current.myEdges[0].myColor != current.myEdges[1].myColor) {
						getColors(current.myEdges[0].myColor, current, 2, current, 2);
						myColors--;
					} else {
						getColors(myUsedColors, current, 2, current, 2);
					        myUsedColors++;
					        myColors++;
					}
				}
			}
		}
	} 
	
	private Polynomial process(long mask) {
		Polynomial result = new Polynomial();
		result.addMonomial(new Monomial(0, 1));
		for (long i = 0; i < myColors - 1; i++) {
			result = Polynomial.mul(result, myC);			
		}
		for (long i = 0; i < myNumInt; i++) {
			if ((mask & (1 << i)) == 0) {
				result = Polynomial.mul(result, myB);
			} else {
				result = Polynomial.mul(result, myA);
			}
		}
		//System.out.println(myColors);
		modifyColors(mask);
		//System.out.println(result);
		return result;
	}
	
	private class ColoredEdge {
		IEdge myEdge;
		boolean myColored = false;

		public ColoredEdge(IEdge edge) {
			myEdge = edge;
		}
	}
	
	private int getTrivialKnotsNumber() {
	        List <IEdge> edges = myKnot.edges();
		HashMap <IEdge, Boolean> visited = new HashMap<IEdge, Boolean> ();
		int result = 0;
		boolean existsNonTrivialKnot = false;
		for (IEdge current : edges) {
			visited.put(current, false);
		}
		for (IEdge edge : edges) {
			IEdge current = edge;
			boolean trivial = true;
			boolean seen = true;
			while (!visited.get(current)) {
				seen = false;
				visited.put(current, true);
				if (numberOfIntersectionsWithEdge(current) != 0) {
					trivial = false;
				}
				current = getAnotherEdgeWithPoint(current.getTarget(), current);
			}
			if (trivial && !seen) {
				result++;
			} else if (!trivial) {
				existsNonTrivialKnot = true;
			}
		}
		if (!existsNonTrivialKnot) {
			result--;
		}
		return result;
	}
	
	private Polynomial calculateKauffmanBracket() {
		Polynomial result = new Polynomial();
		for (long i = 0; i < (1 << (long)myNumInt); i++) {
			result = Polynomial.add(result, process(i));
		}
		for (int i = 0; i < getTrivialKnotsNumber(); i++) {
			result = Polynomial.mul(result, myC);
		}
		return result;
	}
	
	private Polynomial calculateJonesPolynomialWithoutSubstitution() {
		Polynomial result = calculateKauffmanBracket();
		int degree = 0;
		for (IIntersection intersection : myKnot.intersections()) {
			IntPoint2D first = new IntPoint2D(intersection.getUpper().getTarget().getX() - intersection.getUpper().getSource().getX(), 
			intersection.getUpper().getTarget().getY() - intersection.getUpper().getSource().getY());
			IntPoint2D second = new IntPoint2D(intersection.getLower().getTarget().getX() - intersection.getUpper().getSource().getX(), 
			intersection.getLower().getTarget().getY() - intersection.getUpper().getSource().getY());
			if (first.crossProduct(second) < 0) {
				degree++;
			} else {
				degree--;
			}
		}
//		System.out.println(degree);
		if (degree > 0) {
			for (int i = 0; i < degree; i++) {
//				System.out.println(result);
				result = Polynomial.mul(result, myD);
			}
		} else {
			for (int i = 0; i < -degree; i++) {
				result = Polynomial.mul(result, myE);
			}
		}
		return result;
	}

	private int getGcd(int a, int b) {
		while (b != 0) {
			int tmp;
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	private String substitution(Monomial monomial) {
		int coefficient = Math.abs(monomial.getCoefficient());
		String coeff = "";
		if (Math.abs(coefficient) != 1) {
			coeff = coefficient + "";
		}
		String pow;
		int power = monomial.getPower();
		if (power == 0) {
			return coefficient + "";
		}
		int denominator = 4;
		int gcd = getGcd(Math.abs(power), denominator);
		power /= gcd;
		denominator /= gcd;
		if (denominator != 1) {
			pow = power + "/" + denominator;
		} else {
			pow = power + "";
		}
		return coeff + "x" + "<sup>" + pow + "</sup>";
	}

	public String jonesPolynomial() {
		Polynomial jones = calculateJonesPolynomialWithoutSubstitution();
		StringBuffer result = new StringBuffer();	
		boolean first = true; 
		for (Monomial current : jones.getMonomials()) {
			char sign = current.sign();
			current.setPower(- current.getPower());
			if (!first) {
				result.append(" " + sign + " " + substitution(current));
			} else {
				if (sign == '-') {
					result.append(sign + " ");
				}
				result.append(substitution(current));
			}
			if (first) {
				first = false;
			}
		}
		return "<html>" + result.toString() + "</html>";
	}

	public KnotDiagram(IKnot knot) {
	        myA.addMonomial(new Monomial(1, 1));
	        myB.addMonomial(new Monomial(-1 ,1));
	        myC.addMonomial(new Monomial(-2, -1));
	        myC.addMonomial(new Monomial(2, -1));
	        myD.addMonomial(new Monomial(-3, -1));
	        myE.addMonomial(new Monomial(3, -1));
		myKnot = knot;
	        myKnotIntersections = knot.intersections();
	        myNumInt = myKnotIntersections.size();
		myIntersections = new ArrayList <Intersection>();
		for (int i = 0; i < myNumInt; i++) {
			myIntersections.add(new Intersection());
		}
		for (int i = 0; i < myNumInt; i++) {
			for (int j = 0; j < 4; j++) {
				if (myIntersections.get(i).myEdges[j] == null) {
					IPoint point = getPointByIndex(j, myKnotIntersections.get(i));
					if (point == myKnotIntersections.get(i).getUpper().getTarget()) {
						go(myKnotIntersections.get(i).getUpper(), i, j, true);
					}
					if (point == myKnotIntersections.get(i).getLower().getTarget()) {
						go(myKnotIntersections.get(i).getLower(), i, j, true);
					}
				}
			}
		}
		for (int i = 0; i < myNumInt; i++) {
		        Intersection current = myIntersections.get(i);
			for (int j = 0; j < 4; j++) {
				if (current.myEdges[j].myColor == -1) {
					getColors(myColors, current, j, current, j);
					myColors++;					
					myUsedColors++;
				}
			}
		}
		//System.out.println(myColors);
		for (int i = 0; i < myNumInt; i++) {
			for (int j = 0; j < 4; j++) {
		        	//System.out.println(myIntersections.get(i).myEdges[j]);
			}
			//System.out.println();
		}                            
//		//System.out.println(calculateKauffmanBracket());
	}
}
