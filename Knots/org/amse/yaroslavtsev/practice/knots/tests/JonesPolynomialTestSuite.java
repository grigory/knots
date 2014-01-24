package org.amse.yaroslavtsev.practice.knots.tests;

import junit.framework.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.*;
import org.amse.yaroslavtsev.practice.knots.math.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public class JonesPolynomialTestSuite extends TestCase {
        
        static final String PATH = "./org/amse/yaroslavtsev/practice/knots/tests/";

        public JonesPolynomialTestSuite(String name) {
                super(name);
        }

        public void test1() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest01.pkd");
                String correctString = "<html>- x<sup>4</sup> + x<sup>3</sup> + x<sup>1</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }


	public void test2() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest02.pkd");
                String correctString = "<html>1</html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test3() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest03.pkd");
                String correctString = "<html>- x<sup>1/2</sup> - x<sup>-1/2</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test4() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest04.pkd");
                String correctString = "<html>- x<sup>5/2</sup> - x<sup>1/2</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test5() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest05.pkd");
                String correctString = "<html>- x<sup>7</sup> + x<sup>6</sup> - x<sup>5</sup> + x<sup>4</sup> + x<sup>2</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test6() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest06.pkd");
                String correctString = "<html>x<sup>15/2</sup> - x<sup>7/2</sup> - x<sup>5/2</sup> - x<sup>3/2</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test7() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest07.pkd");
                String correctString = "<html>- x<sup>8</sup> - x<sup>7</sup> + x<sup>4</sup> + 2x<sup>3</sup> + 2x<sup>2</sup> + x<sup>1</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test8() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest08.pkd");
                String correctString = "<html>x<sup>-1</sup> + x<sup>-3</sup> - x<sup>-4</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test9() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest09.pkd");
                String correctString = "<html>- x<sup>3/2</sup> - 2x<sup>-1/2</sup> + x<sup>-3/2</sup> - x<sup>-5/2</sup> + x<sup>-7/2</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test10() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest10.pkd");
                String correctString = "<html>- x<sup>6</sup> - x<sup>4</sup> + 2x<sup>3</sup> + 2x<sup>2</sup> + 2x<sup>1</sup> + 2 - x<sup>-1</sup> - x<sup>-3</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test11() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest11.pkd");
                String correctString = "<html>- x<sup>10</sup> + x<sup>9</sup> - x<sup>8</sup> + x<sup>7</sup> - x<sup>6</sup> + x<sup>5</sup> + x<sup>3</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }

	public void test12() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                reader.process(testKnot, PATH + "JonesPolynomialTest12.pkd");
                String correctString = "<html>- x<sup>3</sup> + 3x<sup>2</sup> - 2x<sup>1</sup> + 4 - 2x<sup>-1</sup> + 3x<sup>-2</sup> - x<sup>-3</sup></html>";
                KnotDiagram diagram = new KnotDiagram(testKnot);
                assertEquals(correctString, diagram.jonesPolynomial());
        }
}
