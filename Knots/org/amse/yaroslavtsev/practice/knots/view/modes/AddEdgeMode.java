package org.amse.yaroslavtsev.practice.knots.view.modes;

import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;

import org.amse.yaroslavtsev.practice.knots.model.*;

import org.amse.yaroslavtsev.practice.knots.view.*;

public class AddEdgeMode extends AbstractMode {

    private IEdge myTargetEdge = null;
    private IPoint myTargetPoint = null;
    private IPoint mySourcePoint = null;
    private boolean myMousePressed = false;    
    
    public AddEdgeMode(String text, String accelerator, KnotPainter painter) {
        super(text, accelerator, painter);
    }
    
	public IEdge getAnotherEdgeWithPoint(IPoint point, IEdge first) {
		for (IEdge current : myPainter.getModel().edges()) {
			if ((current.getSource() == point || current.getTarget() == point) && current != first) {
				return current;
			}
		}
		return null;
	}
	
/*	public void modifyDirections(IEdge edge) {
                if (edge == null) {
                        return;
                }
                IEdge sourceEdge = getAnotherEdgeWithPoint(edge.getSource(),edge);		
		IEdge targetEdge = getAnotherEdgeWithPoint(edge.getTarget(),edge);
		if (sourceEdge == null) {
			if (targetEdge != null && targetEdge.getTarget() == edge.getTarget()) {
				edge.rotate();	
			}
			return;		
		}
		if (targetEdge == null) {
			if (sourceEdge.getSource() == edge.getSource()) {
				edge.rotate();
			}
			return;
		}
		if (sourceEdge.getTarget() == edge.getSource()) {
			if (targetEdge.getTarget() == edge.getTarget()) {
				for (IEdge current = targetEdge; current != null; current = getAnotherEdgeWithPoint(current.getTarget(),current)) {
					current.rotate();
				}
			}
			return;
		} 
		if (sourceEdge.getSource() == edge.getSource()) {
			if (targetEdge.getTarget() == edge.getTarget()) {
				edge.rotate();
			} else {
				for (IEdge current = sourceEdge; current != null; current = getAnotherEdgeWithPoint(current.getSource(),current)) {
					current.rotate();
				}	
			}
			return;
		}
	}*/
    
    public void mousePressed(MouseEvent mouse) {
        myMousePressed = true;
        myPainter.startChanges();
        if (mySourcePoint != null) {
            myTargetPoint = myPainter.getModel().addPoint(mySourcePoint.getX(),mySourcePoint.getY());
            myTargetEdge = myPainter.getModel().addEdge(mySourcePoint,myTargetPoint);
//          myPainter.getModel().modifyDirections(myTargetEdge);
            myPainter.select(myTargetPoint);
            myPainter.select(myTargetEdge);
            myPainter.updateAll();                
        }
    }
    
    public void mouseReleased(MouseEvent mouse) {
        myMousePressed = false;
        if (mySourcePoint != null) {
            java.util.List <IPoint> points = myPainter.getModel().points();
            for (IPoint current : points) {
                if (current.getX() == myTargetPoint.getX() && current.getY() == myTargetPoint.getY() && current != myTargetPoint && current != mySourcePoint) {
                    myPainter.getModel().removeEdge(myTargetEdge);
                    myPainter.getModel().removePoint(myTargetPoint);
                    myPainter.unselect(myTargetEdge);                        
                    myTargetEdge = myPainter.getModel().addEdge(mySourcePoint,current);
//                    modifyDirections(myTargetEdge);
                    myPainter.select(myTargetEdge);                        
                    break;
                }
            }
//          modifyDirections(myTargetEdge);    
            myPainter.unselect(mySourcePoint);
            myPainter.unselect(myTargetPoint);            
            myPainter.unselect(myTargetEdge);
            mySourcePoint = null;
            myTargetPoint = null;
            myTargetEdge = null;
	    myPainter.finishChanges();
            myPainter.updateAll();
            mySourcePoint = selectClosestPoint(mouse, mySourcePoint, true);
        }
    }
    
    public void mouseDragged(MouseEvent mouse) {
        if (myTargetPoint != null) {
            java.util.List <IPoint> points = myPainter.getModel().points();
            int dist = 0;
            IPoint closest = null;
            for (IPoint current : points) {
                int curdist = 
                    (myPainter.modelToScreenX(current.getX()) - mouse.getX()) * (myPainter.modelToScreenX(current.getX()) - mouse.getX()) + 
                    (myPainter.modelToScreenY(current.getY()) - mouse.getY()) * (myPainter.modelToScreenY(current.getY()) - mouse.getY());
                if (myPainter.getModel().canAddEdge(myTargetPoint,current) && (closest == null || curdist < dist)) {
                    closest = current;
                    dist = curdist;
                }    
            }
            if (closest != null && dist < KnotPainter.FOCUS_DISTANCE) {
                myTargetPoint.moveTo(closest.getX(),closest.getY());
            } else {
                myTargetPoint.moveTo(
                    myPainter.screenToModelX(mouse.getX()),
                    myPainter.screenToModelY(mouse.getY())
                );                
            }    
            myPainter.updateAll();
        }
    }

    public void mouseMoved(MouseEvent mouse) {
//      System.out.println("Moved");
//      System.out.println(mouse.getX() + " " + mouse.getY());        
        mySourcePoint = selectClosestPoint(mouse, mySourcePoint, true);
 /*       java.util.List <IPoint> points = myPainter.getModel().points();
        int dist = 0;
        IPoint closest = null;
        for (IPoint current : points) {                
            int curdist = 
                (myPainter.modelToScreenX(current.getX()) - mouse.getX()) * (myPainter.modelToScreenX(current.getX()) - mouse.getX()) + 
                (myPainter.modelToScreenY(current.getY()) - mouse.getY()) * (myPainter.modelToScreenY(current.getY()) - mouse.getY());
            if (myPainter.getModel().canAddEdge(current) && (closest == null || curdist < dist)) {
                closest = current;
                dist = curdist;
            }    
        }
        if (closest != null && dist < KnotPainter.FOCUS_DISTANCE) {
                   if (mySourcePoint == null) {
                       mySourcePoint = closest;
                myPainter.select(mySourcePoint);
            } else if (closest != mySourcePoint) {
                myPainter.unselect(mySourcePoint);
                mySourcePoint = closest;
                myPainter.select(mySourcePoint);                                
            }
        } else {
            myPainter.unselect(mySourcePoint);
            mySourcePoint = null;
        }                    
        myPainter.updateAll(false);*/
    }

    	public void mouseExited (MouseEvent mouse) {
//                System.out.println(myMousePressed);
                if (!myMousePressed) {
                        myPainter.unselect(mySourcePoint);
                        mySourcePoint = null;
                        myPainter.updateAll();
                }
//	        System.out.println("Exited");
//              System.out.println(mouse.getX() + " " + mouse.getY());        
//              modifyDirections(myTargetEdge);    
//              myPainter.unselect(mySourcePoint);
//              myPainter.unselect(myTargetPoint);            
//              myPainter.unselect(myTargetEdge);
//              mySourcePoint = null;
//              myTargetPoint = null;
//              myTargetEdge = null;
//              myPainter.updateAll();
//              myPainter.finishChanges();
	}

	public boolean isEnabled() {
		for (IPoint current : myPainter.getModel().points()) {
			if (myPainter.getModel().canAddEdge(current)) {
				return true;
			}
		}
		return false;	
    	}
}
