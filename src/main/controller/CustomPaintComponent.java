package main.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.model.Point;
import main.model.Shape;
import main.view.MainWindow;

public class CustomPaintComponent extends Component {
	 
	private static final long serialVersionUID = 1L;
	
	private Graphics graphic;

	public void paint(Graphics g) {
			
		
	}

void draw(Shape shape) 
{
	if(shape instanceof Point)
	{
		graphic.drawLine(((Point) shape).getX(),  ((Point) shape).getY(), ((Point) shape).getX()  + 1,  ((Point) shape).getY()+1 );
	}		

}

}