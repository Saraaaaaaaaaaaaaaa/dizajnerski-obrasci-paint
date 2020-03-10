package main.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import main.model.Line;
import main.model.Point;
import main.model.Rectangle;
import main.model.Shape;
import main.model.Triangle;

public class CustomPaintComponent extends Component {
	 
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapeArray = new ArrayList<Shape>();
	private String shapeSelector = "";
	private Shape pivotShape; 
	private Boolean firstClick = true;
	private Point firstPoint = new Point();
	private Point secondPoint = new Point();
	
	public void setSelectedShape(String shapeSelector) {
		this.shapeSelector = shapeSelector;
	}	

	public CustomPaintComponent() {
		addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
				 if( firstClick )
				 {
					 firstPoint.setX(e.getX());
					 firstPoint.setY(e.getY());
					 firstClick = false;
				 }
			    }
		});
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				
					 secondPoint.setX(e.getX());
					 secondPoint.setY(e.getY());					
					 switch(shapeSelector) {
						case "POINT" :  break;				
						case "LINE":  shapeArray.add(new Line(firstPoint, secondPoint)); break;
						case "TRIANGLE" :
							shapeArray.add(new Triangle(firstPoint, secondPoint)); 
						break;
						case "RECTANGLE" : shapeArray.add(new Rectangle(firstPoint, secondPoint));
						break;
						}
				     
					 //repaint();
					 firstClick = true;
					 pivotShape = null;
				 
			}
		});
		addMouseMotionListener( new MouseMotionAdapter() {
		     public void mouseDragged(MouseEvent e) {
		    	 if( !firstClick) {
		    		 secondPoint.setX(e.getX());
					 secondPoint.setY(e.getY());
					 
					 switch(shapeSelector) {
						case "POINT" : /* TODO: as point*/ break;				
						case "LINE": 
							 pivotShape = new Line(firstPoint, secondPoint);
							 break;
						case "TRIANGLE" :
							 pivotShape = new Triangle(firstPoint, secondPoint);
							 break;
						case "RECTANGLE" : shapeArray.add(new Rectangle(firstPoint, secondPoint));
						break;
						case "SELECT":
							
							break;
						case "SCALE":
							break;
				
						
						}
		    		
		    		 repaint();
		    	 }
		     }
		});
	}

	// Ona se ponavlja pozivom funkcije repaint();
	public void paint(Graphics g) {	
		
		for(Shape shape : shapeArray) {
			draw(shape, g);
			
		}
		if(pivotShape != null)		
			draw(pivotShape, g);

	}

	void draw(Shape shape, Graphics g) {

		if(shape instanceof Triangle) {
			drawAsTriangle((Triangle)shape, g);
		}
		else if(shape instanceof Line)
			drawAsLine((Line)shape, g);
		if(shape instanceof Rectangle) {
			drawAsRectangle((Rectangle)shape, g);
		}
		
		
	}
		

		private void drawAsRectangle(Rectangle shape, Graphics g) {
		// TODO Auto-generated method stub
		
	}

		private void drawAsTriangle(Triangle shape, Graphics g) {
		
			g.drawPolygon(new int[] {shape.getFirst().getX(), shape.getSecond().getX(), shape.getMiddlePoint().getX()}, 
							new int[] {shape.getFirst().getY(), shape.getSecond().getY(), shape.getMiddlePoint().getY()}, 3);
		
	
	}

		private void drawAsLine(Line line, Graphics g) {
		
			g.drawLine(line.getFirst().getX(), line.getFirst().getY(), line.getSecound().getX(), line.getSecound().getY());
		}

}