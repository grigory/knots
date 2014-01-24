package org.amse.yaroslavtsev.practice.knots.tests;

import java.util.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest13 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest13... ");

		IKnot testKnot = KnotFactory.createKnot();
                IPoint testPoint = testKnot.addPoint(0,0);
                testKnot.removePoint(testPoint);		

		String correctString = "[][]";

		if (testKnot.toString().equals(correctString)) System.out.println("OK");
		else 
		{
		        System.out.println("Wrong Answer!");
			System.out.println("Expected: " + correctString);
			System.out.println("Recieved: " + testKnot);
		}		
	}
}