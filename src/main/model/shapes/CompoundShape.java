package main.model.shapes;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.model.ShapeInderface;

public class CompoundShape extends Shape {
    private List<ShapeInderface> children = new ArrayList<>();

    public CompoundShape(ShapeInderface... components) {
        super(0, 0, Color.WHITE, Color.black);
        add(components);
    }

    public void add(ShapeInderface component) {
        children.add(component);
    }

    public void add(ShapeInderface... components) {
        children.addAll(Arrays.asList(components));
    }

    public void remove(ShapeInderface child) {
        children.remove(child);
    }

    public void remove(ShapeInderface... components) {
        children.removeAll(Arrays.asList(components));
    }

    public void clear() {
        children.clear();
    }

    @Override
    public int getX() {
        if (children.size() == 0) {
            return 0;
        }
        int x = children.get(0).getX();
        for (ShapeInderface child : children) {
            if (child.getX() < x) {
                x = child.getX();
            }
        }
        return x;
    }

    @Override
    public int getY() {
        if (children.size() == 0) {
            return 0;
        }
        int y = children.get(0).getY();
        for (ShapeInderface child : children) {
            if (child.getY() < y) {
                y = child.getY();
            }
        }
        return y;
    }

    @Override
    public int getWidth() {
        int maxWidth = 0;
        int x = getX();
        for (ShapeInderface child : children) {
            int childsRelativeX = child.getX() - x;
            int childWidth = childsRelativeX + child.getWidth();
            if (childWidth > maxWidth) {
                maxWidth = childWidth;
            }
        }
        return maxWidth;
    }

    @Override
    public int getHeight() {
        int maxHeight = 0;
        int y = getY();
        for (ShapeInderface child : children) {
            int childsRelativeY = child.getY() - y;
            int childHeight = childsRelativeY + child.getHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight;
    }

    @Override
    public void drag() {
        for (ShapeInderface child : children) {
            child.drag();
        }
    }

    @Override
    public void drop() {
        for (ShapeInderface child : children) {
            child.drop();
        }
    }

    @Override
    public void moveTo(int x, int y) {
        for (ShapeInderface child : children) {
            child.moveTo(x, y);
        }
    }
    @Override
	public void scaleTo(int x, int y) {
    	children.get(0).scaleTo(x, y);
		
	}

    @Override
    public void moveBy(int x, int y) {
        for (ShapeInderface child : children) {
            child.moveBy(x, y);
        }
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        for (ShapeInderface child : children) {
            if (child.isInsideBounds(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setAllColor(Color fill, Color line) {
        super.setFillColor(fill);
        super.setLineColor(line);
        for (ShapeInderface child : children) {
            child.setFillColor(fill);
            child.setLineColor(line);}
        
    }

    @Override
    public void unSelect() {
        super.unSelect();
        for (ShapeInderface child : children) {
            child.unSelect();
        }
    }

    public ShapeInderface getChildAt(int x, int y) {
        for (ShapeInderface child : children) {
            if (child.isInsideBounds(x, y)) {
                return child;
            }
        }
        return null;
    }

    public boolean selectChildAt(int x, int y) {
        ShapeInderface child = getChildAt(x,y);
        if (child != null) {
            child.select();
            return true;
        }
        return false;
    }

    public List<ShapeInderface> getSelected() {
        List<ShapeInderface> selected = new ArrayList<>();
        for (ShapeInderface child : children) {
            if (child.isSelected()) {
                selected.add(child);
            }
        }
        return selected;
    }

    @Override
    public void paint(Graphics graphics) {
        if (isSelected()) {
            enableSelectionStyle(graphics);
            graphics.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            disableSelectionStyle(graphics);
        }

        for (ShapeInderface child : children) {
            child.paint(graphics);
        }
    }

	
}