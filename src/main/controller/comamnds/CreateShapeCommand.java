package main.controller.comamnds;

import java.awt.Color;

import main.model.Editor;
import main.model.shapes.Circle;
import main.model.shapes.Point;
import main.model.shapes.Rectangle;

public class CreateShapeCommand implements Command {
	private Editor editor;
	//private int startX, startY;
	private Point firstPoint;
	private Point secondPoint;
	private Color fill;
	private Color line;
	private String type;

	public CreateShapeCommand(Editor editor) {
		this.editor = editor;
	}

	@Override
    public boolean hasTarget() {
    	return editor.getSelected().size() == 0 ? false : true;
    }
	@Override
	public String getName() {
		return "CREATE SHAPE -> " + type + " :" + firstPoint + " " +secondPoint + " "+ printColor(fill);
	}
	private String printColor(Color color) {
		return "RGB = " + color.getRed() + "," +color.getGreen() + "," + color.getBlue(); 
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
		case "RECTANGLE": editor.addShape(new Rectangle(firstPoint, secondPoint, this.fill, this.line));
			break;
		case "CIRCLE":
			int a = secondPoint.getX() - firstPoint.getX();
			int b = secondPoint.getY() - firstPoint.getY();
			int radius = (int) (Math.sqrt(a * a + b * b) / 2.4);
			if (radius > 2) {
				editor.addShape(new Circle(firstPoint.getX(), firstPoint.getY(), radius, this.fill,this.line));
				editor.log(getName());
			}
			break;
		}

	}

	public void create(String type, Point firstPoint, Point secondPoint, Color fill,Color line) {
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		this.fill = fill;
		this.line = line;
		this.type = type;
		System.out.println(this.line +""+ this.fill);
		
	}
	
	

    
}
