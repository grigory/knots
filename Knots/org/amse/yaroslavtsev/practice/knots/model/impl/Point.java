package org.amse.yaroslavtsev.practice.knots.model.impl;

import org.amse.yaroslavtsev.practice.knots.model.*;

class Point implements IPoint {
	private int myX;
	private int myY;
	private Knot myKnot;

	public Point(int x, int y, Knot knot) {
		myX = x;
		myY = y;
		myKnot = knot;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}
		
	public void moveTo(int x, int y) {
		myX = x;
		myY = y;
		if (myKnot.pointDegree(this) != 0) {
			myKnot.setToUpdate(true);									
		}
	}

	public String toString() {
		return myX + " " + myY;
	}
}
