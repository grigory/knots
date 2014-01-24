package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class AddPointMode extends AbstractMode {

        private IPoint myTargetPoint = null;
	

	public AddPointMode( String text, String accelerator, KnotPainter painter ) {
		super(text, accelerator, painter);
	}	

	public void mousePressed( MouseEvent mouse ) {
		myPainter.startChanges();
		myTargetPoint = myPainter.getModel().addPoint(
			myPainter.screenToModelX( mouse.getX() ),
			myPainter.screenToModelY( mouse.getY() )
		);
		myPainter.select(myTargetPoint);
		myPainter.updateAll();	
	}

	public void mouseReleased( MouseEvent mouse ) {
		if ( myTargetPoint != null ) {
			myPainter.unselect( myTargetPoint );
			myTargetPoint = null;
		        myPainter.finishChanges();
                        myPainter.updateAll();
		}
	}

	public void mouseDragged( MouseEvent mouse ) {
		if ( myTargetPoint != null ) {
			myTargetPoint.moveTo(
				myPainter.screenToModelX( mouse.getX() ),
				myPainter.screenToModelY( mouse.getY() )
			);				
		}
		myPainter.updateAll();
	}

	public boolean isEnabled() {
		return true;
	}
}
