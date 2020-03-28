package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import main.controller.EventManeger;
import main.controller.Observer;
import main.controller.observers.ColorPickerObserver;



public class ColorPicker extends JButton{
	
	private static final long serialVersionUID = 1L;
	private Color currentColor = Color.white;
	private EventManeger eventManeger = new EventManeger();	
	
	
	
	public ColorPicker(TopToolBar parent) {
	
		setMaximumSize(new Dimension(900, 30));
		setText("Inner color");		
		setBackground(currentColor);
		eventManeger.subscribe("UPDATE COLOR",new ColorPickerObserver(parent));
		addItem();
	}
	void addItem() {
		addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentColor = getBackground();
				ColorPickerDialog cl = new ColorPickerDialog(eventManeger, currentColor);				
				cl.setVisible(true);
			}
		});
		
	}
	public Color getSelectedColor() {
		return currentColor;
	}
	
	public void setSelectedColor(Color color) {
		currentColor = color;
	}
	public void updateBackground() {
		setBackground(currentColor);
		
	}

	
}
