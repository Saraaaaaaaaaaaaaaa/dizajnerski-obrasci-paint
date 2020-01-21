package main.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.controller.CustomPaintComponent;
import main.controller.MouseFrameListener;
import main.model.Point;
import main.model.Shape;

public class MainWindow  extends JFrame{

	private static final long serialVersionUID = 1L;
	private  ArrayList<Shape> shapeArray = new ArrayList<Shape>();
	private  CustomPaintComponent paintComponent = new CustomPaintComponent();
	private Graphics graphic;
	
    private TopToolBar toolBar;
    
	public MainWindow() {
			
		setTitle("Projekat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(700, 500));	
		addMouseListener(new MouseFrameListener());
		
		toolBar = new TopToolBar( getPreferredSize().width );	  
		shapeArray.add(new Point(3,10));
		shapeArray.add(new Point(13,100));
		shapeArray.add(new Point(300,10));
		add(toolBar);  
	
		add(paintComponent);
	    
	    pack();		
	    setVisible(true);
	}
	public void paint(Graphics g) {
			
		
		for(Shape shape : shapeArray) {
			draw(shape);
		}
	}

void draw(Shape shape) 
{
	if(shape instanceof Point)
	{
		graphic.drawLine(((Point) shape).getX(),  ((Point) shape).getY(), ((Point) shape).getX()  + 1,  ((Point) shape).getY()+1 );
	}		

}
}
