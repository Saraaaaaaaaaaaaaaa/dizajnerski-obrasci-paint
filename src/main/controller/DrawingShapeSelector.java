package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DrawingShapeSelector implements ActionListener{

	private String selectedShape = "";
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton)e.getSource();			
		System.out.println("Kliknuto je dugme: " + clicked.getText());
		selectedShape = clicked.getText().toUpperCase();
		
		/*switch(clicked.getText().toUpperCase()) {
		
		case "LINIJA": drawLine();
			break;
		}*/
		
	}
	
	String getSelectedShape() {
		return selectedShape;
	}
	


}
