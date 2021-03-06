package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest03 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest03... ");

		IKnot testKnot = KnotFactory.createKnot();
  
		testKnot.addPoint(0,0);
		testKnot.addPoint(1,1);
		testKnot.addPoint(-1,-2);
		testKnot.addPoint(1,1);
		testKnot.addPoint(0,100);
		String correctString = "[0 0, 1 1, -1 -2, 1 1, 0 100][]";		

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}
	
	}
}