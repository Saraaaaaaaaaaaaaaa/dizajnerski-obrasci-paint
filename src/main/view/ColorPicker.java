package main.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import main.model.BrushColor;

public class ColorPicker extends JComboBox<BrushColor> {
	
	private static final long serialVersionUID = 1L;
	private BrushColor currentColor;
	private ArrayList<BrushColor> colorOptions = new ArrayList<BrushColor>();
	
	public ColorPicker() {
		currentColor = new BrushColor("#ffffff");
		/// colorOptions.add(new BrushColor(Color.red, "RED"));
		/// colorOptions.add(new BrushColor(Color.blue, "BLUE"));
		/// colorOptions.add(new BrushColor(Color.green, "GREEN"));
		
		for(int hex = 0x000001; hex < 0xffffff ; hex += 0x1500) {
			BrushColor cl = new BrushColor("#" + Integer.toHexString( hex));
			addItem(cl);
			
		}
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor= (BrushColor) getSelectedItem();
				
			}
		});
	}
	public BrushColor getSelectedColor() {
		return currentColor;
	}
	
	 
	
}
