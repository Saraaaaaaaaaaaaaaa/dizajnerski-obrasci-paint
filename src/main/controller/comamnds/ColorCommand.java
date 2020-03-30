package main.controller.comamnds;


import java.awt.*;

import main.model.Editor;
import main.model.ShapeInderface;

public class ColorCommand implements Command {
    private Editor editor;
    private Color fill, line;

    public ColorCommand(Editor editor, Color fill, Color line) {
        this.editor = editor;
        this.line = line;
        this.fill = fill;
    }

    @Override
    public String getName() {
    	return  "CHANGE SHAPE COLOR -> " + fill.toString();       
        
    }

    @Override
    public void execute() {
        for (ShapeInderface child : editor.getSelected()) {
            child.setFillColor(fill);
            child.setLineColor(line);
            editor.log(getName());
        }
    }

    @Override
    public boolean hasTarget() {
    	return editor.getSelected().size() == 0 ? false : true;
    }
    
}