package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DrawingShapeSelector implements ActionListener{

	private String selectedShape = "";
	private CustomPaintComponent painter;
	public  DrawingShapeSelector(CustomPaintComponent painter) {
		this.painter = painter;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton)e.getSource();		
		selectedShape = clicked.getText().toUpperCase();
		if( selectedShape != null)
			painter.setSelectedShape( selectedShape);
		
	}
	
	public String getSelectedShape() {
		return selectedShape;
	}
	


}
