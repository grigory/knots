package org.amse.yaroslavtsev.practice.knots.tests;

import junit.framework.*;
import java.io.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.*;
import org.amse.yaroslavtsev.practice.knots.io.*;


public class ReaderTestSuite extends TestCase {

	static final String PATH = "./org/amse/yaroslavtsev/practice/knots/tests/";	
	
	public ReaderTestSuite(String name) {
		super(name);
	}	

	public void test1() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest01.pkd");
		String correctString = "[][][]";
		assertEquals(correctString,testKnot.toString());					
	}

	public void test2() {		
      		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest02.pkd");
		String correctString = "[0 0][][]";
		assertEquals(correctString,testKnot.toString());
	}

	public void test3() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest03.pkd");
		String correctString = "[0 0, 100 100, -20 3][][]";
		assertEquals(correctString,testKnot.toString());
	}

	public void test4() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest04.pkd");
		String correctString = "[0 0, 100 100, -20 3][0 0 -> 100 100, 100 100 -> -20 3][]";
		assertEquals(correctString,testKnot.toString());
	}

	public void test5() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();    
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest05.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 0 1, 0 2, 0 3, 0 4, 0 5, 0 6][0 0 -> 0 1, 0 1 -> 0 2, 0 2 -> 0 0, 0 3 -> 0 4, 0 4 -> 0 6][]";
			String correctMessage = "Intersection not found";
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}

	public void test6() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest06.pkd");
		String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
		assertEquals(correctString,testKnot.toString());
	}
	
	public void test7() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest07.pkd");
		String correctString = "[139 250, 317 12, 293 245, 206 42, 371 152][139 250 -> 317 12, 317 12 -> 293 245, 293 245 -> 206 42, 206 42 -> 371 152, 371 152 -> 139 250][139 250 -> 317 12 is upper than 293 245 -> 206 42, 139 250 -> 317 12 is upper than 206 42 -> 371 152, 317 12 -> 293 245 is upper than 206 42 -> 371 152, 317 12 -> 293 245 is upper than 371 152 -> 139 250, 293 245 -> 206 42 is upper than 371 152 -> 139 250]";
//		System.out.println();
//		System.out.println(correctString);
//		System.out.println(testKnot.toString());
		assertEquals(correctString,testKnot.toString());
	}

/*	public void test8() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		reader.process(testKnot,PATH + "ReaderKnotTest08.pkd");
		String correctString = "[141 282, 314 143, 139 112, 354 309, 539 59, 540 322, 422 202, 695 198, 407 341, 611 81, 182 418, 82 210, 126 163, 289 418, 708 91][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282, 539 59 -> 540 322, 540 322 -> 422 202, 422 202 -> 695 198, 695 198 -> 407 341, 407 341 -> 611 81, 611 81 -> 539 59, 126 163 -> 289 418, 82 210 -> 708 91][141 282 -> 314 143 is upper than 139 112 -> 354 309, 539 59 -> 540 322 is upper than 422 202 -> 695 198, 695 198 -> 407 341 is upper than 539 59 -> 540 322, 540 322 -> 422 202 is upper than 695 198 -> 407 341, 540 322 -> 422 202 is upper than 407 341 -> 611 81, 407 341 -> 611 81 is upper than 422 202 -> 695 198, 407 341 -> 611 81 is upper than 539 59 -> 540 322, 141 282 -> 314 143 is upper than 126 163 -> 289 418, 354 309 -> 141 282 is upper than 126 163 -> 289 418, 126 163 -> 289 418 is upper than 82 210 -> 708 91, 141 282 -> 314 143 is upper than 82 210 -> 708 91, 139 112 -> 354 309 is upper than 82 210 -> 708 91, 539 59 -> 540 322 is upper than 82 210 -> 708 91, 407 341 -> 611 81 is upper than 82 210 -> 708 91]";
		assertEquals(correctString,testKnot.toString());
	}*/

	public void test8() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest08.pkd");
		} catch (KnotException e) {
			String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
			String correctMessage = ("The same intersection found more than once");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}

	public void test9() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest09.pkd");
		} catch (KnotException e) {
			String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
			String correctMessage = ("Not all intersections found");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}

	public void test10() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest10.pkd");
		} catch (KnotException e) {
			String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
			String correctMessage = ("Intersection not found");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test11() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest11.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][0 0 -> 100 100, 100 100 -> -20 3][]";
			String correctMessage = ("Duplicate edge ID found: 0\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}

	public void test12() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest12.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][100 100 -> -20 3][]";
			String correctMessage = ("Invalid edge ID found: wrong\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test13() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest13.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][0 0 -> 100 100, 100 100 -> -20 3][]";
			String correctMessage = ("ID for edge not specified\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test14() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest14.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][][]";
			String correctMessage = ("Invalid source point ID found: wrong\nInvalid target point ID found: wrong\nEdge source not specified\nEdge target not specified\nID of source point 3 was not listed in points\nID of target point -1 was not listed in points\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}

	public void test15() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest15.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][0 0 -> 100 100, 100 100 -> -20 3][]";
			String correctMessage = ("Unexpected tag extra in block <edges> found\nUnexpected tag extra in block <edges> found\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test16() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest16.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0][][]";
			String correctMessage = ("Unexpected tag extra in block <points> found\nID for point not specified\nInvalid point ID found: text\nCoordinate x not specified\nInvalid x coordinate found: text\nCoordinate y not specified\nInvalid y coordinate found: text\nDuplicate point ID found: 0\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test17() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest17.pkd");
		} catch (KnotException e) {
			String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
			String correctMessage = ("Edge can't be added to the knot\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test18() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest18.pkd");
		} catch (KnotException e) {
			String correctString = "[141 282, 314 143, 139 112, 354 309][141 282 -> 314 143, 314 143 -> 139 112, 139 112 -> 354 309, 354 309 -> 141 282][141 282 -> 314 143 is upper than 139 112 -> 354 309]";
			String correctMessage = ("Unexpected tag extra found\nUnexpected tag extrapoint in block <points> found\nUnexpected tag more found\nUnexpected tag extraedge in block <edges> found\nUnexpected tag text found\nUnexpected tag text found\nUnexpected tag extraintersection in block <intersections> found\nIntersection's upper edge not specified\nIntersection's lower edge not specified\nInvalid upper edge ID found: text\nInvalid lower edge ID found: text\nID of upper edge 5 was not listed in edges\nID of lower edge -1 was not listed in edges\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test19() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest19.pkd");
		} catch (KnotException e) {
			String correctString = "[][][]";
			String correctMessage = ("Tag <points> was not found on its place\nTag <edges> was not found on its place\nTag <intersections> was not found on its place\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test20() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest20.pkd");
		} catch (KnotException e) {
			String correctString = "[][][]";
			String correctMessage = ("Tag <knot> not found");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
        public void test21() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest21.pkd");
		} catch (KnotException e) {
			String correctString = "[][][]";
			String correctMessage = ("Syntax errors occurred in the file");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
	
	public void test22() {
       		IKnot testKnot = KnotFactory.createKnot();
		XMLKnotReader reader = new XMLKnotReader();
		try {
			reader.process(testKnot,PATH + "ReaderKnotTest22.pkd");
		} catch (KnotException e) {
			String correctString = "[0 0, 100 100, -20 3][0 0 -> 100 100, 100 100 -> -20 3, -20 3 -> 0 0][]";
			String correctMessage = ("Some directions in the file were wrong and had to be corrected\n");
			assertEquals(correctMessage, e.getMessage());
			assertEquals(correctString, testKnot.toString());
			return;
		}
		fail();
	}
}
