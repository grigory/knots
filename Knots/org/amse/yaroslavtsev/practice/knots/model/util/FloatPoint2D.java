package org.amse.yaroslavtsev.practice.knots.model.util;

import org.amse.yaroslavtsev.practice.knots.model.*;

public final class FloatPoint2D {
	private double myX, myY;

	public FloatPoint2D(double x, double y) {
		myX = x;
		myY = y;
	}
	
	public double getX() {
		return myX;	
	}

	public double getY() {
		return myY;	
	}

	public void subtract(double x, double y) {
		myX -= x;
		myY -= y;
	}
	
	public void subtract(FloatPoint2D point) {
		subtract(point.getX(), point.getY());
	}
	
	public double norm() {
		return myX * myX + myY * myY;		
	}

	public double crossProduct(FloatPoint2D point) {
		return myX * point.getY() - myY * point.getX();		
	}

	public double innerProduct(FloatPoint2D point) {
		return myX * point.getX() + myY * point.getY();
	}

	public static FloatPoint2D getIntersectionPoint(IEdge first, IEdge second) {
        	IPoint p1 = first.getSource();
	        IPoint p2 = first.getTarget();
	        IPoint p3 = second.getSource();
	        IPoint p4 = second.getTarget();
	        long x1 = p1.getX(), x2 = p2.getX(), x3 = p3.getX(), x4 = p4.getX();
	        long y1 = p1.getY(), y2 = p2.getY(), y3 = p3.getY(), y4 = p4.getY();
	        long ax = x2 - x1, bx = x3 - x4, cx = x3 - x1;
	        long ay = y2 - y1, by = y3 - y4, cy = y3 - y1;
	        long det = ax * by - bx * ay, deta = cx * by - bx * cy, detb = ax * cy - cx * ay;
	        if (deta * det <= 0 || detb * det <= 0 || Math.abs(deta) >= Math.abs(det) || Math.abs(detb) >= Math.abs(det) || det == 0) {
	            	return null;
	        } else {
	         	double alpha = (double)(deta) / det;
	         	return new FloatPoint2D ((x1 + alpha * (x2 - x1)), (y1 + alpha * (y2 - y1)));
		}
    	}
}
