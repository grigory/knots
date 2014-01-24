package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest11 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest11... ");

		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		testKnot.addEdge(testSource,testSource);
		
		String correctString = "[0 0][]";		

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
	
	}
}