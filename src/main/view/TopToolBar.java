package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import main.controller.CustomPaintComponent;
import main.controller.DrawingShapeSelector;
import main.controller.EventManeger;
import main.controller.observers.ColorPickerObserver;

public class TopToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private DrawingShapeSelector shapeSelector;
	private CustomPaintComponent painter;
	private HashMap<String, JButton> options = new HashMap<>();

	public TopToolBar(int frameWidth, CustomPaintComponent paintComponent) {

		this.painter = paintComponent;
		shapeSelector = new DrawingShapeSelector(paintComponent);
		
		setMaximumSize(new Dimension(frameWidth, 40));
		// setBackground(Color.green);
		addButtonToToolbar("Point");
		addButtonToToolbar("Line");
		addButtonToToolbar("Triangle");
		addButtonToToolbar("Square");
		addButtonToToolbar("Rectangle");
		addButtonToToolbar("Circle");
		addButtonToToolbar("Delete", false);

	}

	private void addButtonToToolbar(String buttonText) {

		JButton button = new JButton(buttonText);
		button.addActionListener(shapeSelector);
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}

	private void addButtonToToolbar(String buttonText, boolean status) {

		JButton button = new JButton(buttonText);
		button.setEnabled(status);
		button.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				painter.getEditor().deleteSelectedShapes();	
				painter.notifyManager("DELETE BUTTON", "DISABLE");
				painter.setSelectedShape("SELECT");
				painter.repaint();
				
			}
		});
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}

	public void enableDeleteButton() {

		JButton delButton = options.get("DELETE");
		delButton.setEnabled(true);

	}

	public void disableDeleteButton() {
		JButton delButton = options.get("DELETE");
		delButton.setEnabled(false);
	}
}
