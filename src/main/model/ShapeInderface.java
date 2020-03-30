package main.model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public interface ShapeInderface extends Serializable {
    int getX();
    int getY();
 
    int getWidth();
    int getHeight();
    void drag();
    void drop();
    void moveTo(int x, int y);
    void moveBy(int x, int y);
    void scaleTo(int x, int y);
    boolean isInsideBounds(int x, int y);
    boolean isOnEdges(int x , int y);
    Color getFillColor();
    Color getLineColor();
    void setFillColor(Color color);
    void setLineColor(Color color);
    void select();
    void unSelect();
    boolean isSelected();
    void paint(Graphics graphics);
}