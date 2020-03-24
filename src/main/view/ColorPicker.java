package main.view;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import main.controller.Observer;



public class ColorPicker extends JComboBox<Color> {
	
	private static final long serialVersionUID = 1L;
	private Color currentColor;
	private List<Observer> observers = new ArrayList<Observer>();
	private TopToolBar view;
	
	@SuppressWarnings("unchecked")
	public ColorPicker(TopToolBar parent) {
		this.view = parent;
		generateOptions();
		setRenderer(new ColorPickerRender());
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = (Color) getSelectedItem();
				view.getEventManeger().notifyObservers();
				
			}
		});
	}
	private void generateOptions() {
		 int[] values = new int[] { 0, 128, 192, 255 };
		    for (int r = 0; r < values.length; r++)
		      for (int g = 0; g < values.length; g++)
		        for (int b = 0; b < values.length; b++) {
		          Color c = new Color(values[r], values[g], values[b]);
		          addItem(c);
		        }
		
	}
	public Color getSelectedColor() {
		return currentColor;
	}
	

	
}
