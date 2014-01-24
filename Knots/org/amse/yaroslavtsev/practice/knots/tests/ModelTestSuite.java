package org.amse.yaroslavtsev.practice.knots.tests;

import junit.framework.*;
import java.util.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

public class ModelTestSuite extends TestCase {
	public ModelTestSuite(String name) {
		super(name);
	}	

	public void test1() {
		IKnot testKnot = KnotFactory.createKnot();
		String correctString = "[][][]";
		assertEquals(correctString,testKnot.toString());					
	}

	public void test2() {		
		IKnot testKnot = KnotFactory.createKnot();
  
		testKnot.addPoint(0,0);
		String correctString = "[0 0][][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test3() {
		IKnot testKnot = KnotFactory.createKnot();
  
		testKnot.addPoint(0,0);
		testKnot.addPoint(1,1);
		testKnot.addPoint(-1,-2);
		testKnot.addPoint(1,1);
		testKnot.addPoint(0,100);
		String correctString = "[0 0, 1 1, -1 -2, 1 1, 0 100][][]";				
		assertEquals(correctString,testKnot.toString());					
	}

	public void test4() {
		IKnot testKnot = KnotFactory.createKnot();
  
		testKnot.addPoint(0,0);
		testKnot.addPoint(1,1);
		IPoint testPoint = testKnot.addPoint(-1,-2);
	        testKnot.removePoint(testPoint);

		String correctString = "[0 0, 1 1][][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test5() {
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

		String correctString = "[1 1, -20 5, -3 3][][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test6() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		IPoint testTarget = testKnot.addPoint(1,1);
		testKnot.addEdge(testSource,testTarget);
		
		String correctString = "[0 0, 1 1][0 0 -> 1 1][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test7() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(1,1);
		IPoint testPoint3 = testKnot.addPoint(2,2);
		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint2,testPoint3);
		testKnot.addEdge(testPoint3,testPoint1);
		
		String correctString = "[0 0, 1 1, 2 2][0 0 -> 1 1, 1 1 -> 2 2, 2 2 -> 0 0][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test8() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(1,1);
		IPoint testPoint3 = testKnot.addPoint(2,2);
		IPoint testPoint4 = testKnot.addPoint(3,3);

		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint3,testPoint2);
		testKnot.addEdge(testPoint3,testPoint4);
		testKnot.addEdge(testPoint2,testPoint4);
		testKnot.addEdge(testPoint4,testPoint2);
		
		String correctString = "[0 0, 1 1, 2 2, 3 3][0 0 -> 1 1, 1 1 -> 2 2, 2 2 -> 3 3][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test9() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		IPoint testTarget = testKnot.addPoint(1,1);
		testKnot.addEdge(testSource,testTarget);
		testKnot.addEdge(testSource,testTarget);
		testKnot.addEdge(testTarget,testSource);	
		
		String correctString = "[0 0, 1 1][0 0 -> 1 1][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test10() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		IPoint testTarget = testKnot.addPoint(1,1);
		IEdge testEdge = testKnot.addEdge(testSource,testTarget);
		testKnot.removeEdge(testEdge);
		
		String correctString = "[0 0, 1 1][][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test11() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		testKnot.addEdge(testSource,testSource);
		
		String correctString = "[0 0][][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test12() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testSource = testKnot.addPoint(0,0);
		IPoint testTarget = testKnot.addPoint(1,1);
		IEdge testEdge = testKnot.addEdge(testSource,testTarget);
		testKnot.addEdge(testSource,testTarget);
		testKnot.removeEdge(testEdge);
		testKnot.addEdge(testSource,testTarget);					
		String correctString = "[0 0, 1 1][0 0 -> 1 1][]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test13() {
		IKnot testKnot = KnotFactory.createKnot();
                IPoint testPoint = testKnot.addPoint(0,0);
                testKnot.removePoint(testPoint);		

		String correctString = "[][][]";

		assertEquals(correctString,testKnot.toString());					
	}

	public void test14() {
		IKnot testKnot = KnotFactory.createKnot();
                IPoint testPoint1 = testKnot.addPoint(0,0);
                IPoint testPoint2 = testKnot.addPoint(1,1);
                IPoint testPoint3 = testKnot.addPoint(2,2);
                IEdge testEdge1 = testKnot.addEdge(testPoint1,testPoint2);
                IEdge testEdge2 = testKnot.addEdge(testPoint2,testPoint3);
                IEdge testEdge3 = testKnot.addEdge(testPoint3,testPoint1);
                testKnot.removeEdge(testEdge1);
		testKnot.removePoint(testPoint1);

		String correctString = "[1 1, 2 2][1 1 -> 2 2][]";
		assertEquals(correctString,testKnot.toString());					
	}

	public void test15() {
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

		String correctString = "[1 1, 2 2, 3 3, 4 4, 5 5][1 1 -> 3 3, 5 5 -> 1 1][]";
		assertEquals(correctString,testKnot.toString());					
	}

	public void test16() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(0,1);
		IPoint testPoint3 = testKnot.addPoint(1,0);
		IPoint testPoint4 = testKnot.addPoint(1,1);

		IEdge testEdge1 = testKnot.addEdge(testPoint1,testPoint4);
		IEdge testEdge2 = testKnot.addEdge(testPoint2,testPoint3);

		List <IIntersection> intersections = testKnot.intersections();
		
		String correctString = "[0 0, 0 1, 1 0, 1 1][0 0 -> 1 1, 0 1 -> 1 0][0 0 -> 1 1 is upper than 0 1 -> 1 0]";		
		assertEquals(correctString,testKnot.toString());					
	}

	public void test17() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(0,1);
		IPoint testPoint3 = testKnot.addPoint(1,0);

		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint2,testPoint3);
		testKnot.addEdge(testPoint1,testPoint3);
		
		assertEquals(testKnot.isComplete(),true);					
	}
	
	public void test18() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(0,1);
		IPoint testPoint3 = testKnot.addPoint(1,0);

		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint2,testPoint3);
		
		assertEquals(testKnot.isComplete(),false);					
	}	

	public void test19() {
		IKnot testKnot = KnotFactory.createKnot();
  
		IPoint testPoint1 = testKnot.addPoint(0,0);
		IPoint testPoint2 = testKnot.addPoint(0,1);
		IPoint testPoint3 = testKnot.addPoint(1,0);

		testKnot.addEdge(testPoint1,testPoint2);
		testKnot.addEdge(testPoint2,testPoint3);
		testKnot.clear();

		String correctString = "[][][]";				
		assertEquals(correctString, testKnot.toString());					
	}

	public void test20() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		IPoint testPoint4 = testKnot.addPoint(4,4);
		testKnot.addEdge(testPoint1, testPoint2);		
		testKnot.addEdge(testPoint3, testPoint4);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3, 4 4][1 1 -> 2 2, 3 3 -> 4 4, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test21() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		IPoint testPoint4 = testKnot.addPoint(4,4);
		testKnot.addEdge(testPoint1, testPoint2);		
		testKnot.addEdge(testPoint3, testPoint4);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3, 4 4][1 1 -> 2 2, 3 3 -> 4 4, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test22() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		IPoint testPoint4 = testKnot.addPoint(4,4);
		testKnot.addEdge(testPoint1, testPoint2);		
		testKnot.addEdge(testPoint4, testPoint3);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3, 4 4][1 1 -> 2 2, 3 3 -> 4 4, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test23() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		IPoint testPoint4 = testKnot.addPoint(4,4);
		testKnot.addEdge(testPoint2, testPoint1);		
		testKnot.addEdge(testPoint3, testPoint4);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3, 4 4][1 1 -> 2 2, 3 3 -> 4 4, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test24() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		IPoint testPoint4 = testKnot.addPoint(4,4);
		testKnot.addEdge(testPoint2, testPoint1);		
		testKnot.addEdge(testPoint4, testPoint3);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3, 4 4][2 2 -> 1 1, 4 4 -> 3 3, 3 3 -> 2 2][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test25() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		testKnot.addEdge(testPoint1, testPoint2);		
		testKnot.addEdge(testPoint2, testPoint3);		
		String correctString = "[1 1, 2 2, 3 3][1 1 -> 2 2, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
	public void test26() {
		IKnot testKnot = KnotFactory.createKnot();
		IPoint testPoint1 = testKnot.addPoint(1,1);
		IPoint testPoint2 = testKnot.addPoint(2,2);
		IPoint testPoint3 = testKnot.addPoint(3,3);
		testKnot.addEdge(testPoint1, testPoint2);		
		testKnot.addEdge(testPoint3, testPoint2);		
		String correctString = "[1 1, 2 2, 3 3][1 1 -> 2 2, 2 2 -> 3 3][]";
		assertEquals(correctString, testKnot.toString());
	}
	
}
