package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.util.*;
import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.math.*;

public class ChangeIntersectionTypeMode extends AbstractMode {

        private IIntersection myTargetIntersection = null;
	
	public final static int FOCUS_DISTANCE = 64;
	
	public ChangeIntersectionTypeMode(String text, String accelerator, KnotPainter painter) {
		super(text, accelerator, painter);
	}	

	public void mousePressed( MouseEvent mouse ) {
		if (myTargetIntersection != null) {	
		        myPainter.startChanges();		
		}
	}	
	
	public void mouseReleased(MouseEvent mouse) {
		if (myTargetIntersection != null) {
			myTargetIntersection.changeType();
		        myPainter.finishChanges();
                        myPainter.updateAll();
		}
	}
	
	public void mouseMoved(MouseEvent mouse) {
			
		int dist = 0;
		
		List <IIntersection> intersections = myPainter.getModel().intersections();
		IIntersection closest = null;
		for (IIntersection intersection : intersections) {
			IntPoint2D  current = new IntPoint2D(FloatPoint2D.getIntersectionPoint(intersection.getUpper(),intersection.getLower()));
			int curdist = 
		        	(current.getX() - mouse.getX()) * (current.getX() - mouse.getX()) + 
		        	(current.getY() - mouse.getY()) * (current.getY() - mouse.getY());
			if (closest == null || curdist < dist) {
				closest = intersection;
				dist = curdist;
			}	
		}
		if (closest != null && dist < FOCUS_DISTANCE) {
                       	if (myTargetIntersection == null) {
	                       	myTargetIntersection = closest;
				myPainter.select(myTargetIntersection);
			} else if (closest != myTargetIntersection) {
				myPainter.unselect(myTargetIntersection);
				myTargetIntersection = closest;
				myPainter.select(myTargetIntersection);								
			}
		} else if (myTargetIntersection != null) {
			myPainter.unselect(myTargetIntersection);
			myTargetIntersection = null;
		}					
		myPainter.updateAll(false);                    
	}    

	public boolean isEnabled() {
		return (myPainter.getModel().intersections().size() > 0);
	}
}            
