package main.controller.comamnds;


import java.awt.*;

import main.model.Editor;
import main.model.ShapeInderface;

public class ColorCommand implements Command {
    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color) {
        this.editor = editor;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Colorize: " + color.toString();
    }

    @Override
    public void execute() {
        for (ShapeInderface child : editor.getSelected()) {
            child.setColor(color);
        }
    }
}