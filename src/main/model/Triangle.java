package main.model;

public class Triangle extends Shape {

	private Point middlePoint;
	
	
	public Triangle() {
		super.setName("TRIANGLE");
	}

	public Triangle(Point firstPoint, Point secondPoint) {
		super.setFirst(new Point(firstPoint));
		super.setSecond(new Point(secondPoint));
		middlePoint = new Point(firstPoint.getX() + (firstPoint.getX() - secondPoint.getX())/2, secondPoint.getY() );
	}

	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middlePoint) {
		this.middlePoint = middlePoint;
	}

}
