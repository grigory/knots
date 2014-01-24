package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest12 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest12... ");

		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		IPoint testTarget = testKnot.addPoint(1,1);
		IEdge testEdge = testKnot.addEdge(testSource,testTarget);
		testKnot.addEdge(testSource,testTarget);
		testKnot.removeEdge(testEdge);
		testKnot.addEdge(testSource,testTarget);					
		String correctString = "[0 0, 1 1][0 0 -> 1 1]";		

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
	
	}
}