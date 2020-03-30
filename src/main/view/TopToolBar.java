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
import main.controller.listeners.DeleteButtonListener;
import main.controller.listeners.ModifyButtonListener;
import main.controller.observers.ColorPickerObserver;

public class TopToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private DrawingShapeSelector shapeSelector;
	private ColorPicker colorPicker ;
	private CustomPaintComponent painter;
	private HashMap<String, JButton> options = new HashMap<>();

	public TopToolBar(int frameWidth, CustomPaintComponent paintComponent) {

		this.painter = paintComponent;
		shapeSelector = new DrawingShapeSelector(paintComponent);
		
		setMaximumSize(new Dimension(frameWidth, 40));
		addButtonToToolbar("Point");
		addButtonToToolbar("Line");
		addButtonToToolbar("Triangle");
		addButtonToToolbar("Square");
		addButtonToToolbar("Rectangle");
		addButtonToToolbar("Circle");
		addButtonToToolbar("Delete", false, new DeleteButtonListener(painter));
		addButtonToToolbar("Modify", false, new ModifyButtonListener(painter));
		addColorPicker();
		addUndoRedoButtons();

	}

	private void addUndoRedoButtons() {
		addButtonToToolbar("Undo", false, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(painter.getEditor().undo()) {
					painter.repaint();
					painter.notifyManager("MANAGING BUTTONS", "ENABLE REDO");
				}else
					painter.notifyManager("MANAGING BUTTONS", "DISABLE UNDO" );
				
			}
		});
		
		addButtonToToolbar("Redo",false, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(painter.getEditor().redo()) {
					painter.repaint();
					painter.notifyManager("MANAGING BUTTONS", "ENABLE UNDO");
				}
				else
					painter.notifyManager("MANAGING BUTTONS", "DISABLE REDO" );
			}
		});
		
	}

	private void addColorPicker() {
		colorPicker = new ColorPicker(this);
		add(colorPicker);
		
	}

	private void addButtonToToolbar(String buttonText) {

		JButton button = new JButton(buttonText);
		button.addActionListener(shapeSelector);
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}

	private void addButtonToToolbar(String buttonText, boolean status, AbstractAction listener) {

		JButton button = new JButton(buttonText);
		button.setEnabled(status);
		button.addActionListener(listener);
		options.put(buttonText.toUpperCase(), button);
		add(button);
	}

	public void setCurrentLineColor(String option) {
		colorPicker.setSelectedLineColor(new Color(Integer.parseInt(option),true));
		
	}
	public void setCurrentFillColor(String option) {
		colorPicker.setSelectedFillColor(new Color(Integer.parseInt(option),true));
		
	}
	public ColorPicker getColorPicker() {
		return colorPicker;
	}

/*	public void updateCurrentColor() {
		colorPicker.updateBackground();
		
	}
*/
	public void setButtonStatusByName(String name, boolean status) {
		options.get(name).setEnabled(status);	
		
	}

	public void updateView() {
		
		
	}

	



}
