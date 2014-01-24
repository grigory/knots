package org.amse.yaroslavtsev.practice.knots.view.modes;

import javax.swing.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class AboutMode extends AbstractAction {
	private KnotPainter myPainter;

	public AboutMode(String text, String accelerator, KnotPainter painter) {
		putValue(NAME, text);
		myPainter = painter;
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
	}

	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(myPainter, "Knots\nVersion 0.1 beta\nAuthor: Grigory Yaroslavtsev\nmail: grigory.yaroslavtsev@gmail.com\n2007","About", JOptionPane.PLAIN_MESSAGE);
	}

//	public boolean isEnabled() {
//		return (myPainter.getModel().points().size() != 0 && myPainter.getModel().isComplete());
//	}
}
