package org.amse.yaroslavtsev.practice.knots.main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;	
import java.awt.geom.*;
import java.io.File;
import javax.swing.filechooser.*;


import org.amse.yaroslavtsev.practice.knots.io.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.impl.KnotFactory;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class MainWindow extends JFrame{

	private IKnot myTestKnot;
        private KnotPainter myKnotView;
        private ChangeModeToolBar myToolBar;
        private MouseAdapter myMousePressListener;
	private MouseMotionAdapter myMouseMoveListener;
	private MainMenu myMainMenu;
//	private FormulaPanel myFormulaPanel;

        private MainWindow() {
        	super();	
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(900,600);
		IKnot myTestKnot = KnotFactory.createKnot();
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		//content.add( myMainMenu, BorderLayout.NORTH );
//		myFormulaPanel = new FormulaPanel(myTestKnot);
//		content.add(myFormulaPanel, BorderLayout.SOUTH);
		myKnotView = new KnotPainter(myTestKnot, this);
		content.add( myKnotView, BorderLayout.CENTER );
		myMainMenu = new MainMenu(myKnotView);
		myToolBar = new ChangeModeToolBar(myKnotView);
		content.add(myToolBar, BorderLayout.NORTH);
		setJMenuBar(myMainMenu);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}	
		});
		setVisible(true);
	}
		
	public void close() {
		if (myKnotView.getSavedStatus() == false) {
       			int result = JOptionPane.showConfirmDialog(myKnotView, "Do you want to save changes?",
			"Save", JOptionPane.YES_NO_CANCEL_OPTION);
	                if (result == JOptionPane.YES_OPTION) {
				if (myKnotView.getFile() != null) {
					XMLKnotWriter writer = new XMLKnotWriter();     
					writer.process(myKnotView.getModel(), myKnotView.getFile().getName());
				} else {
					JFileChooser fileChooser = new JFileChooser();
					FileFilter filter = new KnotFileFilter();
					fileChooser.setFileFilter(filter);
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
					fileChooser.setDialogTitle("Save");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int res = fileChooser.showSaveDialog(null);
					if (res == JFileChooser.APPROVE_OPTION) {
						File selected = fileChooser.getSelectedFile();
	   					if (!filter.accept(selected)) {
	       						selected = new File(selected.getName() + "." + KnotFileFilter.EXTENSION);      
					       	}
						XMLKnotWriter writer = new XMLKnotWriter();
						writer.process(myKnotView.getModel(), selected.getName());
					}
				}
			} else {
				if (result == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		} 
		System.exit(0);
	}

	public void updateTitle(String title) {
		setTitle(title);
	}

	public static void main( String args[] ) {
//	        System.out.println(MainWindow.class.getPackage().getFullName());
		new MainWindow();
	}

}



