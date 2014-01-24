package org.amse.yaroslavtsev.practice.knots.view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.net.*;
import java.beans.*;

import org.amse.yaroslavtsev.practice.knots.io.*;

import org.amse.yaroslavtsev.practice.knots.view.modes.*;

public class ChangeModeToolBar extends JToolBar {
	ButtonGroup myButtonGroup;
	JToggleButton myButton;
	KnotPainter myView;
	JLabel myFormulaLabel;
			
	public static final String PATH = "/org/amse/yaroslavtsev/practice/knots/view";
	
    	private String[] myIcons = new String[5]; {
		myIcons[0] = PATH + "/img/AddPoint.png";
		myIcons[1] = PATH + "/img/AddEdge.png";
		myIcons[2] = PATH + "/img/MovePoint.png";
		myIcons[3] = PATH + "/img/ChangeIntersectionType.png";
		myIcons[4] = PATH + "/img/Remove.png";
//		myIcons[5] = PATH + "/img/dir4.png";
	}
	
	public ChangeModeToolBar(KnotPainter view) {
		super();
		myView = view;
		add(Box.createHorizontalStrut(12));
		myButtonGroup = new ButtonGroup();                
		int iconIndex = 0;
		for (AbstractAction mode : view.getEditModes()) {
			JToggleButton newButton = new JToggleButton(mode);
			mode.addPropertyChangeListener(new ButtonSelectedPropertyChangeListener(newButton));
			URL u = ChangeModeToolBar.class.getResource(myIcons[iconIndex++]);
			newButton.setIcon(new ImageIcon(u));
			newButton.setText("");
			myButtonGroup.add(newButton);
			add(Box.createHorizontalStrut(2));
		        add(newButton);
		}

/*		for ( AbstractFileMode mode : view.getFileModes() ) {
			JToggleButton newButton = new JToggleButton(mode);
			myButtonGroup.add(newButton);
			add(Box.createHorizontalStrut(2));
		        add(newButton);
		
		}*/
/*		JToggleButton myButton = new JToggleButton("Kauffman's bracket");
		myButton.addItemListener(new Listener());
		myButtonGroup.add( myButton );
		add(Box.createHorizontalStrut(2));
	        add(myButton);
		myFormulaLabel = new JLabel();
		add(myFormulaLabel);*/
/*		myOpenKnotButton = new JToggleButton( "Open Knot" );
		myOpenKnotButton.addItemListener( new OpenKnotListener() );
		myButtonGroup.add(myOpenKnotButton);
		add( Box.createHorizontalStrut( 2 ) );
	        add( myOpenKnotButton );

		mySaveKnotButton = new JToggleButton( "Save Knot" );
		mySaveKnotButton.addItemListener( new SaveKnotListener() );
		myButtonGroup.add( mySaveKnotButton );
		add( Box.createHorizontalStrut( 2 ) );
	        add( mySaveKnotButton );	*/	
//		System.out.println(myButton);
		setFloatable(false);
		setVisible(true);
	}
	
/*	class Listener implements ItemListener {
	 	public void itemStateChanged(ItemEvent e) {
			if (!myView.getModel().isComplete()) {
				return;
			}
			KnotDiagram diagram = new KnotDiagram(myView.getModel());
			myFormulaLabel.setText(diagram.calculateKauffmanBracket().toString());				
		}	
	}*/

/*	class SaveKnotListener implements ItemListener {
	 	public void itemStateChanged(ItemEvent e) {
			if ( mySaveKnotButton.isSelected() ) {
				XMLKnotWriter writer = new XMLKnotWriter();
				writer.write( myView.getModel(), "./org/amse/yaroslavtsev/practice/knots/io/KnotTest.xml" );
				myView.repaint();
			} 
		}	
	}	*/

	class ButtonSelectedPropertyChangeListener implements PropertyChangeListener {
		
		private JToggleButton myButton;
		
		public ButtonSelectedPropertyChangeListener (JToggleButton button) {
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
