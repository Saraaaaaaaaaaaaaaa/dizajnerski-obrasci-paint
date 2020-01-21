package main.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

import main.controller.DrawingShapeSelector;

public class TopToolBar extends JToolBar{

	private static final long serialVersionUID = 1L;
	public TopToolBar(int frameWidth) {
		
		 setSize(new Dimension(frameWidth,  30)); //koju sirinu uzima?
		
		    addButtonToToolbar("Tacka");
		    addButtonToToolbar("Linija");
		    addButtonToToolbar("Trougao");
		    addButtonToToolbar("Kvadrat");
		    addButtonToToolbar("Pravougaonik");
		    addButtonToToolbar("Krug");
		 
	}
	private void addButtonToToolbar(String buttonText) { //zasto je private?
		
		JButton button = new JButton(buttonText);
		button.addActionListener(new DrawingShapeSelector());
		add(button);
	}

}
