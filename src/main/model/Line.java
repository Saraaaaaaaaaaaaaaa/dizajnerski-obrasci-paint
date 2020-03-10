package main.model;

public class Line extends Shape {

	public Line() {
		super.setName("LINE");
	}
	public Line(int x1, int y1, int x2, int y2) {
		
		super.setFirst(new Point(x1,y1));
		super.setSecond(new Point(x2, y2));			
		
	}
	public Line(Point a, Point b)
	{
		super.setFirst(new Point(a));
		super.setSecond(new Point(b));	
	}
	public Point getFirst() {
		return super.getFirst();
	}
	public void setFirst(Point first) {
		super.setFirst(first);
	}
	public Point getSecound() {
		return super.getSecond();		
	}
	public void setSecound(Point second) {
		super.setSecond(second);
	}
}
