package main.model;


import javax.swing.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

import main.controller.Command;
import main.controller.Memento;
import main.model.shapes.Shape;



public class Editor extends JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ArrayList<Shape> allShapes = new ArrayList<Shape>();
    private History history;

    public Editor() {
        history = new History();
    }
    
    public void addShape(Shape shape) {
    	allShapes.add(shape);
    }
  
    public void loadShapes(ShapeInderface... shapes) {
        /*allShapes.clear();        
        allShapes.addAll(shapes);
        canvas.refresh();*/
    }
    public ShapeInderface getChildAt(int x, int y) {
		for(Shape shape: allShapes) {
			if(shape.isInsideBounds(x, y)) {
				return shape;
			}
		}
		return null;
	}
    public ArrayList<Shape> getSelected() {
    	ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
    	for(Shape shape: allShapes) {
			if( shape.isSelected())
			selectedShapes.add(shape);
		}
    	return selectedShapes;
    	
	}
    public void unSelect() {
    	for(Shape shape: allShapes) {
			shape.unSelect();
			System.out.println(" >> " + shape.isSelected());
		}
		
	}
    public  ArrayList<Shape> getShapes() {
        return allShapes;
    }

    public void execute(Command c) {
        history.push(c, new Memento(this));
        c.execute();
    }

    public boolean undo() {
        return history.undo();
    }
           

    public boolean redo() {
        return history.redo();
          
    }

    public String backup() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.allShapes);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            return "";
        }
    }

    @SuppressWarnings("unchecked")
	public void restore(String state) {
        try {
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            this.allShapes = ( ArrayList<Shape>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException occurred.");
        } catch (IOException e) {
            System.out.print("IOException occurred.");
        }
    }

	public void bringToFront(Shape target) {
		if( target != null) {
			allShapes.remove(target);
			allShapes.add(target);
		}
		
	}

	

	

	
}