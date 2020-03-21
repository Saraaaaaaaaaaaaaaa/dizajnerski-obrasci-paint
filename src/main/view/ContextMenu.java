package main.view;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import main.controller.CustomPaintComponent;
import main.model.Editor;

import main.model.shapes.Shape;

public class ContextMenu  extends JPopupMenu{
	JMenuItem bringToFront;
	JMenuItem bringToBack;
	JMenuItem bringFront;
	JMenuItem bringBack;
	Integer x = null;
	Integer y = null;
	CustomPaintComponent source;
	public ContextMenu() {
		bringToFront = new JMenuItem(new AbstractAction(" Bring to front") {
		    public void actionPerformed(ActionEvent e) {
		    	if( x != null && y != null ) {
		    		Shape target =(Shape) source.getEditor().getChildAt(x.intValue(),y.intValue());
		    		source.getEditor().bringToFront(target);
		    		source.repaint();
		    	}
		    }
		});
		bringToBack = new JMenuItem(new AbstractAction(" Bring to back") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( x != null && y != null ) {
		    		Shape target =(Shape) source.getEditor().getChildAt(x.intValue(),y.intValue());
		    		source.getEditor().bringToBack(target);
		    		source.repaint();
		    	}
				
			}
		});
		bringFront = new JMenuItem(new AbstractAction(" Bring front") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( x != null && y != null ) {
		    		Shape target = (Shape) source.getEditor().getChildAt(x.intValue(),y.intValue());
		    		source.getEditor().bringFront(target);
		    		source.repaint();
		    	}
				
			}
		});
		bringBack = new JMenuItem(new AbstractAction(" Bring back") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( x != null && y != null ) {
		    		Shape target = (Shape) source.getEditor().getChildAt(x.intValue(),y.intValue());
		    		source.getEditor().bringBack(target);
		    		source.repaint();
		    	}
				
			}
		});

		add(bringToFront);
		add(bringToBack);
		add(bringFront);
		add(bringBack);
	}
	private void validateMenuItemAvailability(int shapePosition) {
	/*	if(source.getEditor().getShapes().size() == 1) {
			bringToFront.setEnabled(false);
			bringToBack.setEnabled(false);
		//	bringFront.setEnabled(false);
			//bringBack.setEnabled(false);
		}else {
			bringFront.setEnabled(true);
			bringBack.setEnabled(true);
		}*/
		System.out.println(shapePosition);
		if(source.getEditor().getShapes().size()-1 == shapePosition)
			bringFront.setEnabled(false);	
		else
			bringFront.setEnabled(true);
		if(0 == shapePosition)
			bringBack.setEnabled(false);
		else
			bringBack.setEnabled(true);

		
	}
	@Override
	public void show(Component arg0, int x, int y) {
		 
		super.show(arg0, x, y);
		this.x = x;
		this.y = y;
		Shape target = (Shape) source.getEditor().getChildAt(x,y);
		int position = source.getEditor().getShapes().indexOf(target);
		if(target != null)
			validateMenuItemAvailability(position);
		else {
			bringToFront.setEnabled(false);
			bringToBack.setEnabled(false);
		}
	}
	public void addSource(CustomPaintComponent source) {
		this.source = source;	
	}
}
