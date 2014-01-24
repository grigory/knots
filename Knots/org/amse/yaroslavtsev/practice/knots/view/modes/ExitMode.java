package org.amse.yaroslavtsev.practice.knots.view.modes;

import org.amse.yaroslavtsev.practice.knots.view.*;
import javax.swing.*;
import java.awt.event.*;

public class ExitMode extends AbstractAction {
	private KnotPainter myPainter;

	public ExitMode(String text, String accelerator, KnotPainter painter) {
		myPainter = painter;
		putValue(NAME, text);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
	}

	public void actionPerformed(ActionEvent e) {
		myPainter.closeMain();
	}
}
