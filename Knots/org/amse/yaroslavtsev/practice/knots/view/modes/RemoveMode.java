package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class RemoveMode extends AbstractMode {

	private IPoint myTargetPoint = null;
	private IEdge myTargetEdge = null;

	public RemoveMode(String text, String accelerator, KnotPainter painter) {
		super(text, accelerator, painter);
	}
	
	public void mousePressed(MouseEvent mouse) {
                if (myTargetPoint != null || myTargetEdge != null) {	
		        myPainter.startChanges();		
		}
	}
	
	public void mouseReleased(MouseEvent mouse) {
                if (myTargetEdge != null) {
			myPainter.getModel().removeEdge(myTargetEdge);
			myTargetEdge = null;
		}
		if (myTargetPoint != null) {
			myPainter.getModel().removePoint(myTargetPoint);
			myTargetPoint = null;
		}
		myPainter.finishChanges();
                myPainter.updateAll();
		myTargetEdge = selectClosestEdge(mouse, myTargetEdge);		
		myTargetPoint = selectClosestPoint(mouse, myTargetPoint);		
	}

	public void mouseMoved(MouseEvent mouse) {
        
/*	        List < IEdge > edges = myPainter.getModel().edges();
	
		int distance = 0;
		IEdge closestEdge = null;
		for (IEdge current : edges) {
			if (isInside(mouse.getX(), mouse.getY(), current)) {
				int currentDistance = getDistance(mouse.getX(), mouse.getY(), current);				
				if (closestEdge == null || currentDistance < distance) {
					closestEdge = current;
					distance = currentDistance;						
				}
			}
		}

		if (closestEdge != null && distance < FOCUS_DISTANCE && closestEdge != myTargetEdge) {
			if (myTargetEdge != null) {
				myPainter.unselect(myTargetEdge);
			}
			myTargetEdge = closestEdge;
			myPainter.select(myTargetEdge);
		} else if (myTargetEdge != null && (closestEdge == null || distance >= FOCUS_DISTANCE)) {
			myPainter.unselect(myTargetEdge);
			myTargetEdge = null;
		}*/
		myTargetEdge = selectClosestEdge(mouse, myTargetEdge);		
		myTargetPoint = selectClosestPoint(mouse, myTargetPoint);		
/*		java.util.List <IPoint> points = myPainter.getModel().points();
	        int dist = 0;
		IPoint closestPoint = null;
		for (IPoint current : points) {
			int curdist = 
			(myPainter.modelToScreenX(current.getX()) - mouse.getX()) * (myPainter.modelToScreenX(current.getX()) - mouse.getX()) + 
			(myPainter.modelToScreenY(current.getY()) - mouse.getY()) * (myPainter.modelToScreenY(current.getY()) - mouse.getY()) ;
			if (closestPoint == null || curdist < dist) {       
				closestPoint = current;
				dist = curdist;
			}    
		}
		if (closestPoint != null && dist < KnotPainter.FOCUS_DISTANCE) {
			if (myTargetPoint == null) {
				myTargetPoint = closestPoint;
				myPainter.select(myTargetPoint);
			} else if (closestPoint != myTargetPoint) {
				myPainter.unselect(myTargetPoint);
				myTargetPoint = closestPoint;
				myPainter.select(myTargetPoint);
			}
		} else {
			myPainter.unselect(myTargetPoint);
			myTargetPoint = null;
		}
		myPainter.updateAll(false);*/
	}	
	
        
        public void mouseExited(MouseEvent mouse) {
                if (myTargetPoint != null) {
                        myPainter.unselect(myTargetPoint);
                        myTargetPoint = null;
                        myPainter.finishChanges();
                        myPainter.updateAll();
                }
        }
        
        public boolean isEnabled() {
		return (myPainter.getModel().edges().size() > 0 || myPainter.getModel().points().size() > 0);		
	}	
}
