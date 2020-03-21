package main.model;

import java.awt.Color;

import javax.swing.JLabel;

public class BrushColor extends JLabel{
	
	private Color value;
	private String name;
	
	public BrushColor(String hex) {
		
		this.value = Color.decode(hex);
		this.name = "Boja " + hex;
		setOpaque(false);
		super.setBackground(Color.green);
		super.setForeground(Color.blue);
	}
	@Override
	public String toString() {
		return name;
	}
	public Color getColor() {
		return value;
	}
	
	

}
