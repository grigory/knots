package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public class NewFileMode extends AbstractFileMode {

	private FileFilter myFilter;

	public NewFileMode(String text, String accelerator, KnotPainter painter) {
		super(text, accelerator, painter);
		myFilter = new KnotFileFilter();
		fileChooser.setFileFilter(myFilter);
//		fileChooser.setSelectedFile(new File(this.myCurrentDiagramFrame.myFileName));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	public void actionPerformed(ActionEvent e) {
		if (myPainter.getSavedStatus() == false) {
			int result = JOptionPane.showConfirmDialog(myPainter, "Do you want to save changes?",
		       	"Save", JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (myPainter.getFile() != null) {
					XMLKnotWriter writer = new XMLKnotWriter();	
					writer.process(myPainter.getModel(), myPainter.getFile().getName());
				} else {
					int res = fileChooser.showSaveDialog(null);
					if (res == JFileChooser.APPROVE_OPTION) {
						File selected = fileChooser.getSelectedFile();
						if (!myFilter.accept(selected)) {
							selected = new File(selected.getName() + "." + KnotFileFilter.EXTENSION);	
						}
						XMLKnotWriter writer = new XMLKnotWriter();
						writer.process(myPainter.getModel(), selected.getPath());
					}
				}
			}
			else if (result == JOptionPane.CANCEL_OPTION) {
				return;	
			}
		}
		myPainter.getModel().clear();
	        myPainter.setModel(myPainter.getModel());
		updateAll(null);
		myPainter.setSavedStatus(true);
        }	
}


