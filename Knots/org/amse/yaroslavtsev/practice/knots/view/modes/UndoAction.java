package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.io.*;
import javax.swing.*;
//import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public class UndoAction extends AbstractAction {
	private IKnot myPendingModel;
	private IKnot myPreviousModel;
	private KnotPainter myPainter;

/*	public UndoMode(String text, KnotPainter painter) {
		myPainter = painter;		
		putValue(NAME, text);
		setEnabled(false);
	}*/

	public UndoAction(String text, String accelerator, KnotPainter painter) {
		myPainter = painter;		
		putValue(NAME, text);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
                myPreviousModel = null;
//		setEnabled(false);
	}

	public void startChanges(IKnot model) {
		myPendingModel = model.getCopy();		
	}

	public void finishChanges() {
		myPreviousModel = myPendingModel;
//		setEnabled(true);
	}
	                              	
	public boolean isEnabled() {
                return (myPreviousModel != null);
        }

//      public IKnot undo() {
//		return myPreviousModel;		
//	}	

        public void disable() {
                myPreviousModel = null;
        }

        public void actionPerformed(ActionEvent event) {
		myPainter.setModel(myPreviousModel);
//                myPreviousModel = null;
                myPainter.updateAll();
//		setEnabled(false);
	}
}
