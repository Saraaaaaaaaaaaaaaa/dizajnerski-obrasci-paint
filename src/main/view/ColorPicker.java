package main.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
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
		addItem();
	}
	void addItem() {
		JButton clr = new JButton(" Open color pick");		
		clr.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentColor = clr.getBackground();
				ColorPickerDialog cl = new ColorPickerDialog();				
				cl.setVisible(true);
			}
		});
		add(clr);
	}
	public Color getSelectedColor() {
		return currentColor;
	}
	

	
}
