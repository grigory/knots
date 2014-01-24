package org.amse.yaroslavtsev.practice.knots.io;

import org.amse.yaroslavtsev.practice.knots.model.*;

public interface XMLKnotProcessor {
	public static final String ID = "id";
	public static final String X = "x";
	public static final String Y = "y";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	public static final String UPPER = "upper";
	public static final String LOWER = "lower";
	public static final String POINTS = "points";
	public static final String EDGES = "edges";
	public static final String INTERSECTIONS = "intersections";
	public static final String KNOT = "knot";
	public static final String POINT = "point";
	public static final String EDGE = "edge";
	public static final String INTERSECTION = "intersection";

	public void process( IKnot myModel, String fileName );
}
