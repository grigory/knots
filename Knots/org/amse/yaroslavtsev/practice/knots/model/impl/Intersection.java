package org.amse.yaroslavtsev.practice.knots.model.impl;

import org.amse.yaroslavtsev.practice.knots.model.*;

class Intersection implements IIntersection {
	private IEdge myUpper;
	private IEdge myLower;

	public Intersection(IEdge upper, IEdge lower) {
		myUpper = upper;
		myLower = lower;
	}

	public IEdge getUpper() {
		return myUpper;
	}

	public IEdge getLower() {
		return myLower;
	}

	public String toString() {
		return (myUpper + " is upper than " + myLower);
	}

	public void changeType() {
		IEdge tmp = myUpper;
		myUpper = myLower;
		myLower = tmp;
	}
}
