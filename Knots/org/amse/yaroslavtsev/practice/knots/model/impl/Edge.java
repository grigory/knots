package org.amse.yaroslavtsev.practice.knots.model.impl;

import org.amse.yaroslavtsev.practice.knots.model.*;

class Edge implements IEdge {
	private IPoint mySource;	
	private IPoint myTarget;
	
	public Edge(IPoint source, IPoint target) {
		mySource = source;
		myTarget = target;
	}

	public IPoint getSource() {
		return mySource;
	}

	public IPoint getTarget() {
		return myTarget;
	}	

	public void rotate() {
		IPoint tmp = mySource;
		mySource = myTarget;
		myTarget = tmp;
	}
	
	public String toString() {
		return mySource + " -> " + myTarget;
	}
}
