package main.controller;

import main.model.Editor;
import main.model.ShapeInderface;
import main.model.shapes.Shape;

public class ResizeCommand implements Command{
	private Editor editor;
	private int startX, startY;
	private int endX, endY;
	
	
	public ResizeCommand(Editor editor) {
		this.editor = editor;
	}
		

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public void start(int x, int y) {
		startX = x;
		startY = y;
		
	}
	public void scale(int x, int y) {
		for(Shape target : editor.getSelected()) 
			target.scaleTo( x, y);
	
	}
	public void stop(int x, int y) {
		endX = x;
		endY = y;
	}

	@Override
	public void execute() {	
		for(Shape target : editor.getSelected()) 
			target.scaleTo(endX, endY);
		
	}

}

