package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import main.controller.EventManeger;
import main.controller.Observer;
import main.controller.observers.ColorPickerObserver;

public class ColorPicker extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton fillColor = new JButton("Fill color");
	private JButton lineColor = new JButton("Line color");
	// private Color currentColor = Color.white;
	private EventManeger eventManeger = new EventManeger();

	public ColorPicker(TopToolBar parent) {

		setMaximumSize(new Dimension(900, 30));
		fillColor.setBackground(Color.WHITE);
		lineColor.setBackground(Color.BLACK);

		fillColor.setName("FILL");
		lineColor.setName("LINE");
		eventManeger.subscribe("UPDATE FILL COLOR", new ColorPickerObserver(parent, "FILL"));
		eventManeger.subscribe("UPDATE LINE COLOR", new ColorPickerObserver(parent, "LINE"));
		
		addlisteners();
		add(fillColor);
		add(lineColor);
	}

	void addlisteners() {
		fillColor.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ColorPickerDialog cl = new ColorPickerDialog(eventManeger, fillColor);
				cl.setVisible(true);
			}
		});
		lineColor.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ColorPickerDialog cl = new ColorPickerDialog(eventManeger, lineColor);
				cl.setVisible(true);
			}
		});

	}

	public Color getSelectedFillColor() {
		return fillColor.getBackground();
	}
	public Color getSelectedLineColor() {
		return lineColor.getBackground();
	}

	public void setSelectedFillColor(Color color) {
		fillColor.setBackground(color);
	}
	public void setSelectedLineColor(Color color) {
		lineColor.setBackground(color);
	}



}
