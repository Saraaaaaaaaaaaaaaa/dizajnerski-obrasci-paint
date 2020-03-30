package main.model.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private static final long serialVersionUID = 1L;
	private Point firstPoint, secondPoint;
	private Color color;
	private Color fill, line;
	public Rectangle(Point A, Point B, Color fill, Color line) {
		
		super(A.getX(), A.getY(), fill ,line);
		firstPoint = new Point(A);
		secondPoint = new Point(B);
		this.fill = fill;
		this.line = line;
	}
	 @Override
	    public int getWidth() {
	        return Math.abs(secondPoint.getX() - firstPoint.getX());
	    }
		    @Override
	    public int getHeight() {
		        return Math.abs(secondPoint.getY() - firstPoint.getY());
	    }
		    @Override
	    public void paint(Graphics graphics) {
	        super.paint(graphics);	        
	        graphics.setColor(color);
	        graphics.fillRect(firstPoint.getX()+1, firstPoint.getY()+1, getWidth()-1, getHeight()-1);
	        graphics.setColor(Color.BLACK);
	        graphics.drawRect(firstPoint.getX(), firstPoint.getY(), getWidth(), getHeight());
	    
	    }
		    @Override	    
		    public boolean isOnEdges(int x, int y) {
		    	return false;
		    	
		    }
		    @Override
		    public boolean isInsideBounds(int x, int y) {
		    	int tolerance = 2;		    	
		    	int x1 = firstPoint.getX();
		    	int y1 = firstPoint.getY();		    	
		    	int x2 = secondPoint.getX();
		    	int y2 = secondPoint.getY();
		    	if(x > x1- tolerance && x < x2 + tolerance){
		    		if(y> y1 - tolerance && y < y2 + tolerance)
		    			return true;
		    	}
		    	return false;
		    }
			@Override
		public void scaleTo(int x, int y) {
				
				
				
		}
	
	
}
