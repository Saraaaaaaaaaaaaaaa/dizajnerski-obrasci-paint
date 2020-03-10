package main.model;

public class Shape {
	private String name;
	private Point first;
	private Point second;
	private Point middlePoint;

	public Shape() {}
	public Shape(Point a, Point b, String name) {
		this.first = new Point(a);
		this.second = new Point(b);
		this.name =name; 
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getFirst() {
		return first;
	}

	public void setFirst(Point first) {
		this.first = first;
	}

	public Point getSecond() {
		return second;
	}

	public void setSecond(Point second) {
		this.second = second;
	}

	
}
