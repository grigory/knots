package org.amse.yaroslavtsev.practice.knots.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.Line2D;

import java.io.*;
import org.amse.yaroslavtsev.practice.knots.view.modes.*;
import org.amse.yaroslavtsev.practice.knots.model.*;
import org.amse.yaroslavtsev.practice.knots.model.util.*;
import org.amse.yaroslavtsev.practice.knots.math.*;
import org.amse.yaroslavtsev.practice.knots.main.*;

public class KnotPainter extends JPanel {
    
    public static final int POINT_RADIUS = 9;
    public static final int SCALEX = 1;
    public static final int SCALEY = 1;
    public static final BasicStroke STROKE = new BasicStroke(4.0f);
    public static final int INTERSECTION_RADIUS = 8;    
    public static final int FOCUS_DISTANCE = 225;
    public static final int ARROW_LENGTH = 20;
    public static final double ARROW_ANGLE = 0.2;
        
    private IKnot myModel;
    private java.util.Set<IElement> mySelectedElements = new HashSet<IElement>();
    private boolean mySavedStatus;
    private File myFile = null;
    private MainWindow myMainWindow;

    private java.util.List<AbstractAction> myEditModes = new ArrayList<AbstractAction>();
    private AbstractMode myActiveMode;
    private AbstractMode myAddPointMode;    

    private java.util.List<AbstractAction> myFileModes = new ArrayList<AbstractAction>();
    private java.util.List<AbstractAction> myMathModes = new ArrayList<AbstractAction>();
    private java.util.List<AbstractAction> myHelpModes = new ArrayList<AbstractAction>();
    private UndoAction myUndoAction;
//    private FormulaPanel myFormulaPanel;                                              

    public KnotPainter(IKnot model, MainWindow mainWindow) {
        myModel = model;        
//	myFormulaPanel = panel;
	myMainWindow = mainWindow;
        myUndoAction = new UndoAction("Undo", "ctrl Z", this);	
        
        myAddPointMode  = new AddPointMode("Add Point", "ctrl P", this);
        myActiveMode = myAddPointMode;
        myEditModes.add(myActiveMode); 
        myEditModes.add(new AddEdgeMode("Add Edge", "ctrl E", this));
        myEditModes.add(new MovePointMode("Move Point", "ctrl M", this));
        myEditModes.add(new ChangeIntersectionTypeMode("Change Intersection Type", "ctrl I", this));
	myEditModes.add(new RemoveMode("Remove", "ctrl R", this));

        myFileModes.add(new NewFileMode("New...", "ctrl N", this));        
	myFileModes.add(new OpenFileMode("Open...", "ctrl O", this));        
        myFileModes.add(new SaveFileMode("Save", "ctrl S", this));               
	myFileModes.add(new SaveAsFileMode("Save as...", "ctrl shift S", this));
	myFileModes.add(new ExitMode("Exit", "ctrl Q", this));

	myMathModes.add(new JonesPolynomialMode("Jones polynomial", "ctrl J", this));

        myHelpModes.add(new AboutMode("About","ctrl A", this));

	updateAll();
	mySavedStatus = true;
    }

	public java.util.List <IEdge> getAdjacentEdges(IPoint point)  {
	        java.util.List <IEdge> adjacent = new ArrayList <IEdge> ();
		for (IEdge edge : myModel.edges()) {
			if (edge.getSource() == point || edge.getTarget() == point) {
				adjacent.add(edge);
			}			
		}		
		return adjacent;
	}
    
    public IKnot getModel() {
        return myModel;
    }    
    
    public java.util.List <AbstractAction> getEditModes() {
        return Collections.unmodifiableList(myEditModes);
    }
    
    public java.util.List <AbstractAction> getFileModes() {
        return Collections.unmodifiableList(myFileModes);
    }

    public java.util.List <AbstractAction> getMathModes() {
        return Collections.unmodifiableList(myMathModes);
    }
    
    public java.util.List <AbstractAction> getHelpModes() {
        return Collections.unmodifiableList(myHelpModes);
    }
    
    public void setMode(AbstractMode mode) {
        if (myActiveMode != null) {
            removeMouseListener(myActiveMode);
            removeMouseMotionListener(myActiveMode);
        }
        myActiveMode = mode;
        if (myActiveMode != null) {
            addMouseListener(myActiveMode);
            addMouseMotionListener(myActiveMode);
        }
    }
   
    public void updateAll() {
        updateAll(true);
    }    

    public void updateAll(boolean changeSavedStatus) {
//      myModel.updateIntersections();
//	myFormulaPanel.updateAll();
	repaint();
        for (Action action : myEditModes) {
            	action.setEnabled(action.isEnabled());
        }
        for (Action action : myFileModes) {
            	action.setEnabled(action.isEnabled());
        }
	for (Action action : myMathModes) {
		action.setEnabled(action.isEnabled());
 	}
	myUndoAction.setEnabled(myUndoAction.isEnabled());
        myMainWindow.updateTitle(getTitle());	
	if (changeSavedStatus) {
                mySavedStatus = false;
        }
    }
    
    public static int modelToScreenX(int x) {
        return SCALEX * x;        
    }

    public static int modelToScreenY(int y) {
        return SCALEY * y;        
    }

    public int screenToModelX(int x) {
        return Math.min(Math.max(0,x / SCALEX),(int)getSize().getWidth());
    }

    public int screenToModelY(int y) {
        return Math.min(Math.max(0,y / SCALEY),(int)getSize().getHeight());
    }

    private void fillCircle(Graphics2D g, int x, int y, int r) {
        g.fillOval(x - r, y - r, 2 * r + 1, 2 * r + 1);        
    }
        
    private void drawPoint(Graphics2D g, IPoint  point) {
        if (mySelectedElements.contains(point)) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }
        fillCircle(g,modelToScreenX(point.getX()),modelToScreenY(point.getY()),POINT_RADIUS);
    }

    private void drawArrowPart(Graphics2D g, IntPoint2D  point, IntPoint2D  source, IntPoint2D  target) {
        IntPoint2D  temp = new IntPoint2D (source.getX() - target.getX(), source.getY() - target.getY());
        int squarenorm = temp.norm();
	if (squarenorm == 0) {
		return;	
	}
	double norm = Math.sqrt(squarenorm);
	double sin = temp.getY() / norm;
	double cos = temp.getX() / norm;
	IntPoint2D  drawPoint = new IntPoint2D (
	(int)(point.getX() * cos - point.getY() * sin + target.getX()),
	(int)(point.getX() * sin + point.getY() * cos + target.getY()));
	g.draw(new Line2D.Double(drawPoint.getX(), drawPoint.getY(), target.getX(), target.getY()));
   }
        
    private void drawEdge(Graphics2D g, IEdge edge) {
        if (mySelectedElements.contains(edge)) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }
        IntPoint2D  source = new IntPoint2D (modelToScreenX(edge.getSource().getX()), modelToScreenX(edge.getSource().getY()));
        IntPoint2D  target = new IntPoint2D (modelToScreenX(edge.getTarget().getX()), modelToScreenX(edge.getTarget().getY()));
        g.drawLine(source.getX(), source.getY(),target.getX(), target.getY());

    	drawArrowPart(g, new IntPoint2D (POINT_RADIUS + (int)(ARROW_LENGTH * Math.cos(ARROW_ANGLE)), (int)(ARROW_LENGTH * Math.sin(ARROW_ANGLE))), source, target);
    	drawArrowPart(g, new IntPoint2D (POINT_RADIUS + (int)(ARROW_LENGTH * Math.cos(ARROW_ANGLE)), - (int)(ARROW_LENGTH * Math.sin(ARROW_ANGLE))), source, target);
    }
        
        public void processMouseEvent(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.NOBUTTON) {
                        super.processMouseEvent(e);
                }
        }
        
//      public void processMouseMotionEvent(MouseEvent e) {
//             if (e.getButton() == MouseEvent.BUTTON1) {
//                      super.processMouseMotionEvent(e);
//              }
//      }

    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)(g1);
        g.setStroke(STROKE);
        for (IEdge currentEdge : myModel.edges())
            drawEdge(g,currentEdge);
        for (IPoint currentPoint : myModel.points())
            drawPoint(g,currentPoint);
        for (IIntersection currentIntersection : myModel.intersections()) {
            IntPoint2D  intersectionPoint = new IntPoint2D(FloatPoint2D.getIntersectionPoint(currentIntersection.getUpper(),currentIntersection.getLower()));
            g.setColor(Color.white);
            int x = intersectionPoint.getX();
            int y = intersectionPoint.getY();
            g.setClip(x - INTERSECTION_RADIUS,y - INTERSECTION_RADIUS, 2 * INTERSECTION_RADIUS, 2 * INTERSECTION_RADIUS);
            fillCircle(g,x,y,INTERSECTION_RADIUS);
            IEdge upperEdge = currentIntersection.getUpper();
            IEdge lowerEdge = currentIntersection.getLower();
            drawEdge(g,upperEdge);
            drawPoint(g,upperEdge.getSource());
            drawPoint(g,upperEdge.getTarget());
            drawPoint(g,lowerEdge.getSource());
            drawPoint(g,lowerEdge.getTarget());
        }
    }        

	public void select(IElement element) {
                select(element, true);        
        }
    
        public void select(IElement element, boolean selectEdges) {
		if (element != null) {
			mySelectedElements.add(element);
			if ((element instanceof IPoint) && selectEdges) {
				java.util.List <IEdge> edges = getAdjacentEdges((IPoint)element);
				for (IEdge edge : edges) {
					select(edge);
				}
			}
		}
	}

	public void unselect(IElement element) {
		if (element != null) {
			mySelectedElements.remove(element);
			if (element instanceof IPoint) {
				java.util.List <IEdge> edges = getAdjacentEdges((IPoint)element);
				for (IEdge edge : edges) {
					unselect(edge);
				}
			}
		}
	}

	public void startChanges() {
		myUndoAction.startChanges(myModel);
	}	
    
        public void finishChanges() {
		myUndoAction.finishChanges();
	}

	public AbstractAction getUndoMode() {
		return myUndoAction;
	}

        public boolean getSavedStatus() {
        	return mySavedStatus;
        }

	public void setSavedStatus(boolean savedStatus) {
		mySavedStatus = savedStatus;
	}

	public File getFile() {
        	return myFile;
        }

        public void setFile(File file) {
        	myFile = file;
        }
    
    	public void setModel(IKnot model) {
    		myModel = model;
 	        myUndoAction.disable();
        }
    
    	public void unselectAll() {
        	mySelectedElements.clear();
    	}

    	public String getTitle() {
		String title;
        	if (myFile != null) {
			title = myFile.getName();
		} else {
			title = "Untitled";
		}
		title = title + " - Knots";	
		return title;
    	}

	public void closeMain() {
		myMainWindow.close();
	}
}
