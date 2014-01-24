package org.amse.yaroslavtsev.practice.knots.model.impl;

import org.amse.yaroslavtsev.practice.knots.model.IKnot;

public class KnotFactory {
	private KnotFactory() {
	}

	public static Knot createKnot() {
		return new Knot();
	}
}
