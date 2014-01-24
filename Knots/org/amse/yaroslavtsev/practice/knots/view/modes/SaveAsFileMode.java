package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.io.File;
import javax.swing.filechooser.*;
import javax.swing.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.io.*;
import org.amse.yaroslavtsev.practice.knots.view.*;

public class SaveAsFileMode extends AbstractFileMode {

        private FileFilter myFilter = new KnotFileFilter();
        
        public SaveAsFileMode(String text, String accelerator, KnotPainter painter) {
        	super(text, accelerator, painter);
		fileChooser.setFileFilter(myFilter);
//		fileChooser.setSelectedFile(new File(this.myCurrentDiagramFrame.myFileName));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
//	    int returnVal = chooser.showSaveDialog(myFrame);
//	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	    	String str = chooser.getSelectedFile().getPath();
//	    	String str1 = chooser.getSelectedFile().getName();
//	    	if (!filter.accept(str)){
//				str += "."+BasicDiagramReader.getFilter();
//				str1 += "."+BasicDiagramReader.getFilter(); 
//			}
		
		fileChooser.setDialogTitle("Save as");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
	
	public File showDialog() {
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selected = fileChooser.getSelectedFile();
//			System.out.println(myFilter);
	        	if (!myFilter.accept(selected)) {
//                              System.out.println(selected.getPath());
                                return new File(selected.getPath() + "." + KnotFileFilter.EXTENSION);	
            
                        } else {
				return selected;
			}
		} else {
			return null;
		}
	}

	public void actionPerformed(ActionEvent e) {
		File file = showDialog();					
		if (file == null) {
			return;
		}
		XMLKnotWriter writer = new XMLKnotWriter();
		writer.process(myPainter.getModel(), file.getPath());
		updateAll(file);
	}
}
