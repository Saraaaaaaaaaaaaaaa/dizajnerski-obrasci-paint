package main.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

import main.controller.CustomPaintComponent;
import main.controller.DrawingShapeSelector;
import main.controller.EventManeger;
import main.controller.observers.ColorPickerObserver;

public class TopToolBar extends JToolBar{

	
	private static final long serialVersionUID = 1L;
	private DrawingShapeSelector shapeSelector ;
	private EventManeger eventManeger = new EventManeger();
	private HashMap<String, JButton> options = new HashMap<>();
	
	public TopToolBar(int frameWidth, CustomPaintComponent paintComponent) {
		eventManeger.subscribe("pick color", new ColorPickerObserver(this));
		
		shapeSelector = new DrawingShapeSelector(paintComponent);
		
		 setSize(new Dimension(frameWidth,  30)); 
		 	addButtonToToolbar("Select");
		 	addButtonToToolbar("Scale");
		    addButtonToToolbar("Point");
		    addButtonToToolbar("Line");
		    addButtonToToolbar("Triangle");
		    addButtonToToolbar("Square");
		    addButtonToToolbar("Rectangle");
		    addButtonToToolbar("Circle");
		    addButtonToToolbar("Delete shape", false);
	}
	
	
	private void addButtonToToolbar(String buttonText) { 
		
		JButton button = new JButton(buttonText);
		button.addActionListener(shapeSelector);
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}

	private void addButtonToToolbar(String buttonText, boolean status) { 
		
		JButton button = new JButton(buttonText);
		button.addActionListener(shapeSelector);
		button.setEnabled(status);
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}


	public EventManeger getEventManeger() {
		return eventManeger;
	}


	public void setEventManeger(EventManeger eventManeger) {
		this.eventManeger = eventManeger;
	}


	public void enableDeleteButton() {
		
		JButton delButton =  options.get("DELETE SHAPE");
		delButton.setEnabled(true);
		
	}


	public void disableDeleteButton() {
		JButton delButton =  options.get("DELETE SHAPE");
		delButton.setEnabled(false);
	}
}
