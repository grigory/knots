package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest15 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest15... ");

		IKnot testKnot = KnotFactory.createKnot();    
		IPoint testPoint1 = testKnot.addPoint(0,0);
		
		IPoint testPoint2 = testKnot.addPoint(1,1);
		IPoint testPoint3 = testKnot.addPoint(2,2);
		IPoint testPoint4 = testKnot.addPoint(3,3);
		IPoint testPoint5 = testKnot.addPoint(4,4);
		IPoint testPoint6 = testKnot.addPoint(5,5);

		IEdge testEdge1 = testKnot.addEdge(testPoint1,testPoint2);
		IEdge testEdge2 = testKnot.addEdge(testPoint4,testPoint1);
		IEdge testEdge3 = testKnot.addEdge(testPoint6,testPoint1);
		IEdge testEdge4 = testKnot.addEdge(testPoint5,testPoint4);
		IEdge testEdge5 = testKnot.addEdge(testPoint3,testPoint6);
		IEdge testEdge6 = testKnot.addEdge(testPoint3,testPoint4);
		                
                testKnot.removePoint(testPoint1);
		IEdge testEdge7 = testKnot.addEdge(testPoint4,testPoint2);
		IEdge testEdge8 = testKnot.addEdge(testPoint4,testPoint5);
		IEdge testEdge9 = testKnot.addEdge(testPoint6,testPoint2);
		IEdge testEdge10 = testKnot.addEdge(testPoint2,testPoint5);
		testKnot.removeEdge(testEdge4);
		testKnot.removeEdge(testEdge5);

		String correctString = "[1 1, 2 2, 3 3, 4 4, 5 5][3 3 -> 1 1, 5 5 -> 1 1]";

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
		
	}
}
