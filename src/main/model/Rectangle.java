package main.model;

public class Rectangle extends Shape {
	private Point topLeft;
	private Point bottomRight;
	
	public Rectangle() {
		super.setName("RECTANGLE");
	}
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		// Ensure topleft uses the smaller of x1,x2 and y1,y2
	    // and topright uses the larger of x1,x2 and y1,y2
		setTopLeft(new Point(Math.min(x1,x2),Math.min(y1,y2)));
		setBottomRight(new Point(Math.max(x1,x2),Math.max(y1,y2)));
	}
	
	public Rectangle(Point tl, Point br) {
		this(tl.getX(), tl.getY(), br.getX(), br.getY());		
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Point bottomRight) {
		this.bottomRight = bottomRight;
	}
	
}
