package org.amse.yaroslavtsev.practice.knots.tests;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class KnotTest01 {

	public static void main(String args[]) {
	
		System.out.print("Running IKnotTest01... ");

		IKnot testKnot = KnotFactory.createKnot();
  
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