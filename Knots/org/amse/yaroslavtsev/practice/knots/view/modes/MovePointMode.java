package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class MovePointMode extends AbstractMode {

        private IPoint myTargetPoint = null;
//	private List <IEdge> myTargetEdges = null;
	private boolean myMousePressed = false;

	public MovePointMode(String text, String accelerator, KnotPainter painter) {
		super(text, accelerator, painter);
	}
	
	public void mousePressed( MouseEvent mouse ) {
		myMousePressed = true;
                if (myTargetPoint != null) {	
		        myPainter.startChanges();		
		}
	}

	public void mouseReleased( MouseEvent mouse ) {
                myMousePressed = false;
//              System.out.println(myTargetPoint);
                if (myTargetPoint != null) {	
		        myPainter.unselect(myTargetPoint);
		        myTargetPoint = null;
                        myPainter.finishChanges();
                        myPainter.updateAll();
                        myTargetPoint = selectClosestPoint(mouse, myTargetPoint);
  //                      System.out.println(myTargetPoint);
                }
	}
	
	public void mouseDragged( MouseEvent mouse ) {
		if ( myTargetPoint != null ) {
			myTargetPoint.moveTo(
				myPainter.screenToModelX(mouse.getX()),
				myPainter.screenToModelY(mouse.getY())
			);				
			myPainter.updateAll();
		}
	}

	public void mouseMoved(MouseEvent mouse) {
		
//		java.util.List <IPoint> points = myPainter.getModel().points();
//                System.out.println(myTargetPoint);
                myTargetPoint = selectClosestPoint(mouse, myTargetPoint);
/*	                int dist = 0;
			IPoint closest = null;
			for (IPoint current : points) {
				int curdist = 
			        	( myPainter.modelToScreenX( current.getX() ) - mouse.getX() ) * ( myPainter.modelToScreenX(current.getX() ) - mouse.getX() ) + 
			        	( myPainter.modelToScreenY( current.getY() ) - mouse.getY() ) * ( myPainter.modelToScreenY(current.getY() ) - mouse.getY() );
				if ( closest == null || curdist < dist ) {
					closest = current;
					dist = curdist;
				}	
			}
			if ( closest != null && dist < KnotPainter.FOCUS_DISTANCE ) {
	                       	if ( myTargetPoint == null ) {
		                       	myTargetPoint = closest;
					myPainter.select( myTargetPoint );
	                                myPainter.updateAll(false);
				} else if ( closest != myTargetPoint ) {
					myPainter.unselect( myTargetPoint ); 
					myTargetPoint = closest;
					myPainter.select( myTargetPoint);
	                                myPainter.updateAll(false);
				}
			} else if ( myTargetPoint != null ) {
				myPainter.unselect( myTargetPoint );
				myTargetPoint = null;
			        myPainter.updateAll(false);
	                }	*/				
	}

        public void mouseExited(MouseEvent mouse) {
//                System.out.println(mouse.getButton());
                if (!myMousePressed && myTargetPoint != null) {
                        myPainter.unselect(myTargetPoint);
                        myTargetPoint = null;
                        myPainter.updateAll();
                }
                /*              if (myTargetPoint != null) {
                //      System.out.println("HERE");
                        myPainter.unselect(myTargetPoint);
                        myTargetPoint = null;
                        myPainter.updateAll();
                        myPainter.finishChanges();
                }*/
        }

	public boolean isEnabled() {
		return (myPainter.getModel().points().size() > 0);
	}
}
