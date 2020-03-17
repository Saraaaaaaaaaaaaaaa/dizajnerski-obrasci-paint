package main.model;

import java.awt.Color;

public class BrushColor {
	
	private Color value;
	private String name;
	public BrushColor(Color val, String name) {
		this.value = val;
		this.name = name;
		
	}
	@Override
	public String toString() {
		return name;
	}
	public Color getColor() {
		return value;
	}
	
	

}
