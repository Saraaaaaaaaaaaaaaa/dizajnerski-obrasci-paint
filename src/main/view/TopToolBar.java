package main.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

import main.controller.CustomPaintComponent;
import main.controller.DrawingShapeSelector;

public class TopToolBar extends JToolBar{

	
	private static final long serialVersionUID = 1L;
	private DrawingShapeSelector shapeSelector ;
	public TopToolBar(int frameWidth, CustomPaintComponent paintComponent) {
		
		shapeSelector = new DrawingShapeSelector(paintComponent);
		 setSize(new Dimension(frameWidth,  30)); 
		 	addButtonToToolbar("Select");
		 	addButtonToToolbar("Scale");
		 	
		    addButtonToToolbar("Point");
		    addButtonToToolbar("Line");
		    addButtonToToolbar("Triangle");
		    addButtonToToolbar("Kvadrat");
		    addButtonToToolbar("Pravougaonik");
		    addButtonToToolbar("Krug");
		 
	}
	
	private void addButtonToToolbar(String buttonText) { 
		
		JButton button = new JButton(buttonText);
		button.addActionListener(shapeSelector);
		add(button);
	}
}
