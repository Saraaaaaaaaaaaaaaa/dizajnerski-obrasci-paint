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
        return "Move by X:" + (endX - startX) + " Y:" + (endY - startY);
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
        for (ShapeInderface child : editor.getSelected()) {
            child.moveBy(endX - startX, endY - startY);
        }
    }
}