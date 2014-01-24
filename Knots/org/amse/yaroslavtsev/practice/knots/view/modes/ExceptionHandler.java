package org.amse.yaroslavtsev.practice.knots.view.modes;

import javax.swing.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.io.*;
import org.amse.yaroslavtsev.practice.knots.view.*;

class ExceptionHandler {
	public static boolean read(XMLKnotReader reader, KnotPainter painter, String path) {
		IKnot copy = painter.getModel().getCopy();
		try {
			reader.process(painter.getModel(), path);
		} catch (KnotException e) {
			if (e.isCritical()) {
				JOptionPane.showMessageDialog(painter, "The file can't be read.\n Error occurred while reading file:\n" + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
				painter.setModel(copy);
				return false;
			} else {
				JOptionPane.showMessageDialog(painter, "Some errors occurred and were skipped while reading the file:\n" + e.getMessage(), 
				"Warning", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		return true;
	}
}
