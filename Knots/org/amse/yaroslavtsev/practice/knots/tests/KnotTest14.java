package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest14 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest14... ");

		IKnot testKnot = KnotFactory.createKnot();
                IPoint testPoint1 = testKnot.addPoint(0,0);
                IPoint testPoint2 = testKnot.addPoint(1,1);
                IPoint testPoint3 = testKnot.addPoint(2,2);
                IEdge testEdge1 = testKnot.addEdge(testPoint1,testPoint2);
                IEdge testEdge2 = testKnot.addEdge(testPoint2,testPoint3);
                IEdge testEdge3 = testKnot.addEdge(testPoint3,testPoint1);
                testKnot.removeEdge(testEdge1);
		testKnot.removePoint(testPoint1);

		String correctString = "[1 1, 2 2][1 1 -> 2 2]";

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
		
	}
}