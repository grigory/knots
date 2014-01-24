package org.amse.yaroslavtsev.practice.knots.view;

import javax.swing.*;
import java.beans.*;

import org.amse.yaroslavtsev.practice.knots.io.*;
import org.amse.yaroslavtsev.practice.knots.view.modes.*;

public class MainMenu extends JMenuBar {

        private KnotPainter myView;
//	private ButtonGroup myFileButtonGroup;
//      private FormulaPanel myFormulaPanel;
	private ButtonGroup myEditButtonGroup;

	public MainMenu(KnotPainter view) {
		myView = view;		
//		myFormulaPanel = panel;
		add(createFileMenu());
		add(createEditMenu());
		add(createMathMenu());
		add(createHelpMenu());
	}

	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");

//		myFileButtonGroup = new ButtonGroup();	
		for (Action mode : myView.getFileModes()) {
			JMenuItem newButton = new JMenuItem(mode);
//			myFileButtonGroup.add(newButton);
		        fileMenu.add(newButton);			
		}
		return fileMenu;
	}
	
	private JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem newButton = new JMenuItem(myView.getUndoMode());
	        editMenu.add(newButton);
		myEditButtonGroup = new ButtonGroup();                
		for (Action mode : myView.getEditModes()) {
			JRadioButtonMenuItem newRadioButton = new JRadioButtonMenuItem(mode);
			mode.addPropertyChangeListener(new MenuItemSelectedPropertyChangeListener(newRadioButton));
			myEditButtonGroup.add(newRadioButton);
		        editMenu.add(newRadioButton);
		}
		return editMenu;
	}

	private JMenu createMathMenu() {
		JMenu mathMenu = new JMenu("Math");
		for (Action mode : myView.getMathModes()) {
			JMenuItem newButton = new JMenuItem(mode);
			mathMenu.add(newButton);
		}
		return mathMenu;
	}
	
        private JMenu createHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		for (Action mode : myView.getHelpModes()) {
			JMenuItem newButton = new JMenuItem(mode);
			helpMenu.add(newButton);
		}
		return helpMenu;
	}

	class MenuItemSelectedPropertyChangeListener implements PropertyChangeListener {
		
		private JRadioButtonMenuItem myButton;
		
		public MenuItemSelectedPropertyChangeListener (JRadioButtonMenuItem button) {
			myButton = button;
		}
		
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName() == "selected") {
				Boolean selected = (Boolean)evt.getNewValue();
				myButton.setSelected(selected.booleanValue());
			}
//			System.out.println(evt.getPropertyName() + evt.getOldValue().toString() + evt.getNewValue().toString());
		}
	}
}
