package org.amse.yaroslavtsev.practice.knots.view.modes;


import javax.swing.*;
//import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.math.*;

public class JonesPolynomialMode extends AbstractAction {
//	private IKnot myModel;
//	private FormulaPanel myFormulaPanel;
	private KnotPainter myPainter;

	public JonesPolynomialMode(String text, String accelerator, KnotPainter painter) {
		putValue(NAME, text);
//		myModel = model;		
//		myFormulaPanel = panel;
		myPainter = painter;
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
	}

	public void actionPerformed(ActionEvent event) {
		KnotDiagram diagram = new KnotDiagram(myPainter.getModel());
		JOptionPane.showMessageDialog(myPainter, diagram.jonesPolynomial(), "Jones polynomial", JOptionPane.PLAIN_MESSAGE);
//		myFormulaPanel.setText("Kauffman bracket: ");
//		myFormulaPanel.setFormula(diagram.calculateKauffmanBracket().toString());		
	}

	public boolean isEnabled() {
		return (myPainter.getModel().points().size() != 0 && myPainter.getModel().isComplete());
	}
}
