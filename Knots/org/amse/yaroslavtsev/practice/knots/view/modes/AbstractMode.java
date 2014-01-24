package org.amse.yaroslavtsev.practice.knots.view.modes;

import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.view.*;
import org.amse.yaroslavtsev.practice.knots.model.*;

public abstract class AbstractMode extends AbstractAction implements MouseInputListener {

	protected KnotPainter myPainter;

	public AbstractMode(String text, String accelerator, KnotPainter painter) {
		myPainter = painter;
		putValue(NAME, text);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	public final static int FOCUS_DISTANCE = 3;

	public int innerProduct(int x1, int y1, int x2, int y2) {
		return x1 * x2 + y1 * y2;
	}
	
	public int sqrNorm(int x, int y) {
		return x * x + y * y;				
	}
	
	public double norm(int x, int y) {
		return Math.sqrt(x * x + y * y);
	}
	
	public boolean isInside(int x, int y, IEdge edge) {
		IPoint source = edge.getSource();
		IPoint target = edge.getTarget();
		int sx = source.getX(), sy = source.getY();
		x -= sx;
		y -= sy;
		int vx = target.getX() - sx;
		int vy = target.getY() - sy;
		int inner = innerProduct(x, y, vx, vy);
		return (inner >= 0 && inner <= sqrNorm(vx, vy));
	}

	public int getDistance(int x, int y, IEdge edge) {
		IPoint source = edge.getSource();
		IPoint target = edge.getTarget();
		int sx = source.getX(), sy = source.getY();
		x -= sx;
		y -= sy;
		int vy = target.getX() - sx;
		int vx = - (target.getY() - sy);
		double inner = innerProduct(x, y, vx, vy);
		inner /= norm(vx, vy);
		return Math.abs((int) inner);
								
	}
        
	public IPoint selectClosestPoint(MouseEvent mouse, IPoint closestPoint) {
		return selectClosestPoint(mouse, closestPoint, false);	
	}

	public IPoint selectClosestPoint(MouseEvent mouse, IPoint closestPoint, boolean checkEdgeAddingPossibility) {
       		if (mouse.getX() < 0 || mouse.getY() < 0 || 
                mouse.getX() > myPainter.getSize().getWidth() || 
                mouse.getY() > myPainter.getSize().getHeight()) {
                        myPainter.unselect(closestPoint);
                        return null;      
                }
                java.util.List <IPoint> points = myPainter.getModel().points();                
		int dist = 0;
		IPoint closest = null;
		for (IPoint current : points) {
			int curdist = 
		        	(myPainter.modelToScreenX(current.getX()) - mouse.getX()) * (myPainter.modelToScreenX(current.getX()) - mouse.getX()) + 
		        	(myPainter.modelToScreenY(current.getY()) - mouse.getY()) * (myPainter.modelToScreenY(current.getY()) - mouse.getY());	
			if ((closest == null || curdist <= dist) && (!checkEdgeAddingPossibility || myPainter.getModel().canAddEdge(current))) {
                                closest = current;
				dist = curdist;
			}	
		}
		if (closest != null && dist < KnotPainter.FOCUS_DISTANCE) {
                       	if (closestPoint == null) {
	                       	closestPoint = closest;
				myPainter.select(closestPoint, !checkEdgeAddingPossibility);
			} else if (closest != closestPoint) {
				myPainter.unselect(closestPoint); 
				closestPoint = closest;
				myPainter.select(closestPoint, !checkEdgeAddingPossibility);
			} 
		} else if (closestPoint != null) {
			myPainter.unselect(closestPoint);
			closestPoint = null;
                }					
                myPainter.updateAll(false);
		return closestPoint;         
	}

	public IEdge selectClosestEdge(MouseEvent mouse, IEdge closestEdge) {
		List < IEdge > edges = myPainter.getModel().edges();
	
		int distance = 0;
		IEdge closest = null;
		for (IEdge current : edges) {
			if (isInside(mouse.getX(), mouse.getY(), current)) {
				int currentDistance = getDistance(mouse.getX(), mouse.getY(), current);				
				if (closest == null || currentDistance < distance) {
					closest = current;
					distance = currentDistance;						
				}
			}
		}

		if (closest != null && distance < FOCUS_DISTANCE && closest != closestEdge) {
			if (closestEdge != null) {
				myPainter.unselect(closestEdge);
			}
			closestEdge = closest;
			myPainter.select(closestEdge);
		} else if (closestEdge != null && (closest == null || distance >= FOCUS_DISTANCE)) {
			myPainter.unselect(closestEdge);
			closestEdge = null;
		}
		myPainter.updateAll(false);
		return closestEdge;	
	}	

	public void actionPerformed(ActionEvent event) {
		myPainter.setMode(this);
		firePropertyChange("selected", false, true);
	}
}
