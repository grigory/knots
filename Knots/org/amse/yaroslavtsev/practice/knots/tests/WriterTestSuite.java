package org.amse.yaroslavtsev.practice.knots.tests;

import java.io.*;

import junit.framework.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;
import org.amse.yaroslavtsev.practice.knots.io.*;


public class WriterTestSuite extends TestCase {

        static final String PATH = "./org/amse/yaroslavtsev/practice/knots/tests/";     

        public WriterTestSuite(String name) {
                super(name);
        }       

        public String getString(File file) {
                StringBuffer result = new StringBuffer();
                DataInputStream data;
                try {
                        data = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));         
                        while (data.available() != 0) {
                                char c = (char)data.readByte();
                                if (c != ' ') {
                                        result.append(c);
                                }
                        }
                } catch (IOException e) {
                }
                return result.toString();
        }
        
        public void test1() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                String input = PATH + "ReaderKnotTest01.pkd";
                String output = PATH + "WriterKnotTest01.pkd";
                reader.process(testKnot,input);
                XMLKnotWriter writer = new XMLKnotWriter();

                writer.process(testKnot,output);
                assertEquals(getString(new File(input)),getString(new File(output)));
        }

        public void test2() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                String input = PATH + "ReaderKnotTest02.pkd";
                String output = PATH + "WriterKnotTest02.pkd";
                reader.process(testKnot,input);
                XMLKnotWriter writer = new XMLKnotWriter();

                writer.process(testKnot,output);
                assertEquals(getString(new File(input)),getString(new File(output)));
        }

        public void test3() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                String input = PATH + "ReaderKnotTest03.pkd";
                String output = PATH + "WriterKnotTest03.pkd";
                reader.process(testKnot,input);
                XMLKnotWriter writer = new XMLKnotWriter();

                writer.process(testKnot,output);
                assertEquals(getString(new File(input)),getString(new File(output)));
        }

        public void test4() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                String input = PATH + "ReaderKnotTest04.pkd";
                String output = PATH + "WriterKnotTest04.pkd";
                reader.process(testKnot,input);
                XMLKnotWriter writer = new XMLKnotWriter();

                writer.process(testKnot,output);
                assertEquals(getString(new File(input)),getString(new File(output)));
        }
        
        public void test5() {
                IKnot testKnot = KnotFactory.createKnot();
                XMLKnotReader reader = new XMLKnotReader();
                String input = PATH + "ReaderKnotTest06.pkd";
                String output = PATH + "WriterKnotTest06.pkd";
                reader.process(testKnot,input);
                XMLKnotWriter writer = new XMLKnotWriter();

                writer.process(testKnot,output);
                assertEquals(getString(new File(input)),getString(new File(output)));
        }
}
