package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest05 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest05... ");

		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(-1,-2);
	        testKnot.removePoint(testPoint2);
		testKnot.removePoint(testPoint1);
		IPoint testPoint3 = testKnot.addPoint(20,20);
		testKnot.addPoint(-20,5);
		testKnot.addPoint(-3,3);
		testKnot.removePoint(testPoint3);

		String correctString = "[1 1, -20 5, -3 3][]";		

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
	
	}
}