package main.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.model.ShapeInderface;

public class Shape implements ShapeInderface {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x, y;
    private int dx = 0, dy = 0;
    private Color fill;
    private Color line;
    private boolean selected = false;

    Shape(int x, int y, Color fill, Color line) {
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.line = line;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public void setX(int newX) {
    	x = newX;
    }

    public void setY(int newY) {
    	y = newY;
    }
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void drag() {
        dx = x;
        dy = y;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = dx + x;
        this.y = dy + y;
    }

    @Override
    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void drop() {
        this.x = dx;
        this.y = dy;
    }
    
    @Override
    public boolean isOnEdges(int x, int y) {
    	int tolerance = 15;
   
    	if( (x < getX() + getWidth() + tolerance) && (x >  getX() + getWidth() - tolerance))
    	{
    		if( (y < getY() + getHeight() + tolerance) &&(y > getY() + getHeight() - tolerance))
    				return true;
    	}
    	return false;
    	
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
    	
        return x > getX() && x < (getX() + getWidth()) &&
                y > getY() && y < (getY() + getHeight());
    }

 
    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void unSelect() {
        selected = false;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    void enableSelectionStyle(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);

        Graphics2D g2 = (Graphics2D) graphics;
        float dash1[] = {2.0f};
        g2.setStroke(new BasicStroke(2.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                2.0f, dash1, 0.0f));
       
        
    }
    void enableSelectionStyleDot(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);

        Graphics2D g2 = (Graphics2D) graphics;
        float dash1[] = {2.0f};
        g2.setStroke(new BasicStroke(2.0f));
        
    }
    void enableSelectionShapeStyle(Graphics graphics) {
        graphics.setColor(Color.RED);

        Graphics2D g2 = (Graphics2D) graphics;
        float dash1[] = {2.0f};
        g2.setStroke(new BasicStroke(2.0f));
       
        
    }

    void disableSelectionStyle(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke());
    }

    @Override
    public void paint(Graphics graphics) {
        if (isSelected()) {
            enableSelectionStyle(graphics);
           /* graphics.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            enableSelectionStyleDot(graphics);
            graphics.drawRect(getX()-3, getY()-3, 6, 6);
            graphics.drawRect(getX() + getWidth()-3, getY()-3, 6, 6);
            graphics.drawRect(getX()-3, getY()+getHeight()-3, 6, 6);
            graphics.drawRect(getX()+getWidth()-3, getY()+getHeight()-3, 6, 6);*/
           // enableSelectionShapeStyle(graphics);
        }else {
            disableSelectionStyle(graphics);
        }
        

    }

	@Override
	public void scaleTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getFillColor() {
		return fill;
	}

	@Override
	public Color getLineColor() {
		return line;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLineColor(Color color) {
		// TODO Auto-generated method stub
		
	}



	public void setAllColor(Color fill, Color line) {
		// TODO Auto-generated method stub
		
	}
}