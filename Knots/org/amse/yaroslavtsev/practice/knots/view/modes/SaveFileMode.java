package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.io.*;

public class SaveFileMode extends AbstractFileMode {
	
        private FileFilter myFilter = new KnotFileFilter();

	public SaveFileMode(String text, String accelerator, KnotPainter painter) {
		super(text,accelerator, painter);
		fileChooser.setFileFilter(myFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setDialogTitle("Save as");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	
	}

	public File showDialog() {
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selected = fileChooser.getSelectedFile();
	        	if (!myFilter.accept(selected)) {
                                return new File(selected.getPath() + "." + KnotFileFilter.EXTENSION);	
            
                        } else {
			        return selected;
			}
		} else {
			return null;
		}
	} 
	public boolean isEnabled() {
		return (myPainter.getSavedStatus() == false);
	}

	public void actionPerformed(ActionEvent e) {
		XMLKnotWriter writer = new XMLKnotWriter();
		if (myPainter.getFile() != null) {
                        writer.process(myPainter.getModel(), myPainter.getFile().getPath());
			updateAll(myPainter.getFile());                
		} else {
                	File file = showDialog();					
			if (file == null) {
				return;
			}
			XMLKnotWriter newWriter = new XMLKnotWriter();
			newWriter.process(myPainter.getModel(), file.getPath());
			updateAll(file);        
       	        }
	}
}

	
	
