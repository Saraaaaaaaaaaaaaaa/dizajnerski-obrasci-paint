package main.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import main.controller.EventManeger;
import main.controller.Observer;
import main.controller.observers.ColorPickerObserver;



public class ColorPicker extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Color currentColor = Color.white;
	private EventManeger eventManeger = new EventManeger();	
	private TopToolBar view;
	
	
	@SuppressWarnings("unchecked")
	public ColorPicker(TopToolBar parent) {
		this.view = parent;
		setMaximumSize(new Dimension(900, 30));

		eventManeger.subscribe("PICK COLOR", new ColorPickerObserver(this));
		generateOptions();
	//	setRenderer(new ColorPickerRender());
	/*	addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = (Color) getSelectedItem();
				eventManeger.notifyObserver("PICK COLOR", currentColor.toString());
				
			}
		});*/
	}
	private void generateOptions() {
		addItem(Color.BLACK);
		for (int g = 0; g < 255; g+=24) {
		       Color c = new Color(255, g ,0);
		          addItem(c);
		}
		for (int r = 255; r > 0; r-=24) {
		       Color c = new Color(r, 255, 255-r);
		          addItem(c);
		}
		for (int g = 255; g > 0; g-=24) {
		       Color c = new Color(255 - g, g, 255);
		          addItem(c);
		}
		for (int b = 255; b > 0 ; b-=48) {
		       Color c = new Color(255,255-b,255);
		          addItem(c);
		}
		
		
		
	}
	void addItem(Color option) {
		Button clr = new Button();
		clr.setBackground(option);
		clr.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentColor = clr.getBackground();
				eventManeger.notifyObserver("PICK COLOR", currentColor.toString());
			}
		});
		add(clr);
	}
	public Color getSelectedColor() {
		return currentColor;
	}
	

	
}
