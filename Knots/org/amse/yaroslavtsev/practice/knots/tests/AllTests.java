package org.amse.yaroslavtsev.practice.knots.tests;

import junit.framework.*;

public class AllTests{
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ModelTestSuite.class);
		suite.addTestSuite(ReaderTestSuite.class);
		suite.addTestSuite(WriterTestSuite.class);
                suite.addTestSuite(JonesPolynomialTestSuite.class);
                suite.addTestSuite(PolynomialTestSuite.class);
                return suite;
	}	
}


