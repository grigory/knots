package org.amse.yaroslavtsev.practice.knots.tests;

import junit.framework.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.*;
import org.amse.yaroslavtsev.practice.knots.math.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public class PolynomialTestSuite extends TestCase {
        
        static final String PATH = "./org/amse/yaroslavtsev/practice/knots/tests/";

        public PolynomialTestSuite(String name) {
                super(name);
        }

        public void test1() {
                Polynomial polynomial = new Polynomial();
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }

        public void test2() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                polynomial.addMonomial(monomial1);
                String correctString = "<html>x<sup>1</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test3() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, 1);
                polynomial.addMonomial(monomial1);
                polynomial.addMonomial(monomial2);
                String correctString = "<html>x<sup>1</sup> + x<sup>2</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }

        public void test4() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(1, 2);
                polynomial.addMonomial(monomial1);
                polynomial.addMonomial(monomial2);
                String correctString = "<html>3x<sup>1</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }

        public void test5() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(-2, -1);
                polynomial.addMonomial(monomial1);
                String correctString = "<html>- x<sup>-2</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }

        public void test6() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(0,0);
                polynomial.addMonomial(monomial1);
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test7() {
                Polynomial polynomial = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(1, -1);
                polynomial.addMonomial(monomial1);
                polynomial.addMonomial(monomial2);
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test8() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial4 = new Polynomial.Monomial(2, -1);
                polynomial2.addMonomial(monomial3);
                polynomial2.addMonomial(monomial4);
                Polynomial polynomial = Polynomial.add(polynomial1, polynomial2); 
                String correctString = "<html>2x<sup>1</sup> - 2x<sup>2</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test9() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial4 = new Polynomial.Monomial(2, -1);
                polynomial2.addMonomial(monomial3);
                polynomial2.addMonomial(monomial4);
                Polynomial polynomial = Polynomial.mul(polynomial1, polynomial2); 
                String correctString = "<html>x<sup>2</sup> - 2x<sup>3</sup> + x<sup>4</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test10() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(0, 0);
                polynomial2.addMonomial(monomial3);
                Polynomial polynomial = Polynomial.mul(polynomial1, polynomial2); 
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test11() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(1, 0);
                polynomial2.addMonomial(monomial3);
                Polynomial polynomial = Polynomial.mul(polynomial1, polynomial2); 
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test12() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(1, -1);
                Polynomial.Monomial monomial4 = new Polynomial.Monomial(2, 1);
                polynomial2.addMonomial(monomial3);
                polynomial2.addMonomial(monomial4);
                Polynomial polynomial = Polynomial.add(polynomial1, polynomial2); 
                String correctString = "<html>0</html>";
                assertEquals(correctString, polynomial.toString());
        }
        
        public void test13() {
                Polynomial polynomial1 = new Polynomial();
                Polynomial.Monomial monomial1 = new Polynomial.Monomial(1, 1);
                Polynomial.Monomial monomial2 = new Polynomial.Monomial(2, -1);
                Polynomial.Monomial monomial5 = new Polynomial.Monomial(-2, -1);
                polynomial1.addMonomial(monomial1);
                polynomial1.addMonomial(monomial2);
                polynomial1.addMonomial(monomial5);
                Polynomial polynomial2 = new Polynomial();
                Polynomial.Monomial monomial3 = new Polynomial.Monomial(1, -1);
                Polynomial.Monomial monomial4 = new Polynomial.Monomial(2, 1);
                Polynomial.Monomial monomial6 = new Polynomial.Monomial(-3, -1);
                polynomial2.addMonomial(monomial3);
                polynomial2.addMonomial(monomial6);
                polynomial2.addMonomial(monomial4);
                Polynomial polynomial = Polynomial.mul(polynomial1, polynomial2); 
                String correctString = "<html>x<sup>-5</sup> - x<sup>-2</sup> + 2x<sup>-1</sup> - x<sup>0</sup> - x<sup>2</sup> + 2x<sup>3</sup> - x<sup>4</sup></html>";
                assertEquals(correctString, polynomial.toString());
        }

}
