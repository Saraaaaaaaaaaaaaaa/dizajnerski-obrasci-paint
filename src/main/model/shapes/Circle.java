package main.model.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	
	private static final long serialVersionUID = 1L;
	private int radius;
	  
	    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }
	    @Override
    public int getWidth() {
        return radius * 2;
    }
	    @Override
    public int getHeight() {
	        return radius * 2;
    }
	    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
        graphics.setColor(super.getColor());
        graphics.fillOval(x+1, y+1, getWidth() - 3, getHeight() - 3);
    
    }
	    @Override	    
	    public boolean isOnEdges(int x, int y) {
	    	int originX = getX() + getWidth()/2;
	    	int originY = getY() + getHeight()/2;
	    	int tolerance = 1;
	    	int parameter =  (int)Math.sqrt((originX-x)*(originX-x)+ (originY-y)*(originY-y));
	    	if(this.radius > parameter - tolerance && this.radius < parameter+tolerance){
	    		return true;
	    	}
	    	return false;
	    }
	    @Override
	    public boolean isInsideBounds(int x, int y) {
	    	int originX = getX() + getWidth()/2;
	    	int originY = getY() + getHeight()/2;
	    	int tolerance = 1;
	    	int parameter =  (int)Math.sqrt((originX-x)*(originX-x)+ (originY-y)*(originY-y));
	    	if(this.radius >= parameter + tolerance ){
	    		return true;
	    	}
	    	return false;
	    }
		@Override
	public void scaleTo(int x, int y) {
			
			this.radius = (int) (Math.sqrt(( x - super.x) * (x-super.x) + (y - super.y)*(y- super.y))*Math.sqrt(2)/4);
			
	}
}


