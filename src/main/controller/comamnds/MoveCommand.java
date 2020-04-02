package main.controller.comamnds;

import main.model.Editor;
import main.model.ShapeInderface;

public class MoveCommand implements Command {
    private Editor editor;
    private int startX, startY;
    private int endX, endY;

    public MoveCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "MOVE BY -> (" + (endX - startX) + " ," + (endY - startY) + ")";
    }
    @Override
    public boolean hasTarget() {
    	return editor.getSelected().size() == 0 ? false : true;
    }
    
    public void start(int x, int y) {
        startX = x;
        startY = y;
        for (ShapeInderface child : editor.getSelected()) {
            child.drag();
        }
    }

    public void move(int x, int y) {
    	
        for (ShapeInderface child : editor.getSelected()) {
            child.moveTo(x - startX, y - startY);
            
        }
    }

    public void stop(int x, int y) {
        endX = x;
        endY = y;
        for (ShapeInderface child : editor.getSelected()) {
            child.drop();
        }
    }

    @Override
    public void execute() {
    	System.out.print("MOVE SHAPES -> ");		
        for (ShapeInderface child : editor.getSelected()) {
            child.moveBy(endX - startX, endY - startY);            
            editor.log(getName());
        }
    }
}