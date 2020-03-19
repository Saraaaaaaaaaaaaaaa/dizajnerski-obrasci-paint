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
		bringToFront = new JMenuItem(new AbstractAction("Bring to front") {
		    public void actionPerformed(ActionEvent e) {
		    	if( x != null && y != null ) {
		    		Shape target =(Shape) source.getEditor().getChildAt(x.intValue(),y.intValue());
		    		source.getEditor().bringToFront(target);
		    		source.repaint();
		    	}
		    }
		});
		bringToBack = new JMenuItem(" Bring to back");
		bringFront = new JMenuItem(" Bring front");
		bringBack = new JMenuItem(" Bring back");
		
		add(bringToFront);
		add(bringToBack);
		add(bringFront);
		add(bringBack);
	}
	@Override
	public void show(Component arg0, int x, int y) {
		 
		super.show(arg0, x, y);
		this.x = x;
		this.y = y;
	}
	public void addSource(CustomPaintComponent source) {
		this.source = source;	
	}
}
