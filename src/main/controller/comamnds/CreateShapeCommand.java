package main.controller.comamnds;

import java.awt.Color;

import main.model.Editor;
import main.model.shapes.Circle;
import main.model.shapes.Point;

public class CreateShapeCommand implements Command {
	private Editor editor;
	//private int startX, startY;
	private Point firstPoint;
	private Point secondPoint;
	private Color color;
	private String type;

	public CreateShapeCommand(Editor editor) {
		this.editor = editor;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		switch (type) {
		case "POINT":
			break;
		case "LINE": // shapeArray.add(new Line(firstPoint, secondPoint)); break;
		case "TRIANGLE":
			// shapeArray.add(new Triangle(firstPoint, secondPoint));
			break;
		case "RECTANGLE":// shapeArray.add(new Rectangle(firstPoint, secondPoint));
			break;
		case "CIRCLE":
			int a = secondPoint.getX() - firstPoint.getX();
			int b = secondPoint.getY() - firstPoint.getY();
			int radius = (int) (Math.sqrt(a * a + b * b) / 2.4);
			if (radius > 2) {
				editor.addShape(new Circle(firstPoint.getX(), firstPoint.getY(), radius, color));
			}
			break;
		}

	}

	public void create(String type, Point firstPoint, Point secondPoint, Color selectedColor) {
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		this.color = selectedColor;
		this.type = type;
	}
}
