package main.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import main.model.BrushColor;

public class ColorPicker extends JComboBox<BrushColor> {
	
	private static final long serialVersionUID = 1L;
	private BrushColor currentColor;
	private ArrayList<BrushColor> colorOptions = new ArrayList<BrushColor>();
	
	public ColorPicker() {
		currentColor = new BrushColor(Color.black, "BLACK");
		colorOptions.add(new BrushColor(Color.red, "RED"));
		colorOptions.add(new BrushColor(Color.blue, "BLUE"));
		colorOptions.add(new BrushColor(Color.green, "GREEN"));
		for(BrushColor color: colorOptions)
			addItem(color);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor= (BrushColor) getSelectedItem();
				System.out.println(currentColor);
				
			}
		});
	}
	public BrushColor getSelectedColor() {
		return currentColor;
	}
	
	 
	
}
