package org.amse.yaroslavtsev.practice.knots.math;

import org.amse.yaroslavtsev.practice.knots.model.util.*;

public class IntPoint2D {
	private int myX, myY;

	public IntPoint2D(FloatPoint2D point) {
		myX = (int)point.getX();
		myY = (int)point.getY();
	}
	
	public IntPoint2D(int x, int y) {
		myX = x;
		myY = y;
	}
	
	public int getX() {
		return myX;	
	}

	public int getY() {
		return myY;	
	}

	public int norm() {
		return myX * myX + myY * myY;		
	}

	public int crossProduct(IntPoint2D point) {
		return myX * point.getY() - myY * point.getX();		
	}
}
