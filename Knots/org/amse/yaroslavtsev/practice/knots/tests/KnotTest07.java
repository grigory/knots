package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest07 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest07... ");

		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(1,1);
		IPoint testPoint3 = testKnot.addPoint(2,2);
		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint2,testPoint3);
		testKnot.addEdge(testPoint3,testPoint1);
		
		String correctString = "[0 0, 1 1, 2 2][0 0 -> 1 1, 1 1 -> 2 2, 2 2 -> 0 0]";		

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
	
	}
}