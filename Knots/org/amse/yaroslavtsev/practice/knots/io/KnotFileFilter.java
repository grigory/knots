package org.amse.yaroslavtsev.practice.knots.io;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class KnotFileFilter extends FileFilter {

	public static final String DESCRIPTION = "Polygonal knot diagram (*.PKD)";
	public static final String EXTENSION = "pkd";
	
	public String getExtension(File f) {
		if(f != null) {
		    	String filename = f.getName();
		    	int i = filename.lastIndexOf('.');
		    	if(i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}    	
    	
    	public boolean accept(File f) {
		if(f != null) {
	    		if(f.isDirectory()) {
				return true;
		    	}
	    		String extension = getExtension(f);
	    		if(extension != null && extension.compareTo(EXTENSION) == 0) {
				return true;
	 	   	}
		}
		return false;
    	}	

	public String getDescription() {
		return DESCRIPTION;
	}
}
