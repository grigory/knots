package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.io.*;
import javax.swing.*;
//import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public abstract class AbstractFileMode extends AbstractAction {

        protected KnotPainter myPainter;
        protected JFileChooser fileChooser;

	public AbstractFileMode(String text, String accelerator, KnotPainter painter) {
		fileChooser = new JFileChooser();
		myPainter = painter;
		putValue(NAME, text);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));	
	}
	
	public void updateAll(File file) {
		myPainter.setSavedStatus(true);
		myPainter.setFile(file);
		myPainter.updateAll();
		myPainter.setSavedStatus(true);
		myPainter.setFile(file);
	}
	
	abstract public void actionPerformed(ActionEvent e);
}
