package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
//import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import main.controller.CustomPaintComponent;
import main.controller.EventManeger;
import main.controller.observers.ColorPickerObserver;
import main.model.Editor;

public class MainWindow  extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private  CustomPaintComponent paintComponent;
	private Editor editor ;
	private ColorPicker colorPicker ;
    public TopToolBar toolBar;
    
	public MainWindow() {
			
		setTitle("Projekat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(700, 500));	
		editor = new Editor();
		
		paintComponent =  new CustomPaintComponent(this); 
		toolBar = new TopToolBar( getPreferredSize().width, paintComponent );
		paintComponent.setSource(this);		
		paintComponent.setBackground(new Color(255,5,5));
		colorPicker = new ColorPicker(toolBar);
		toolBar.add(colorPicker);
		add(toolBar);  
	
		add(paintComponent);	    
	
		
	    pack();		
	    setVisible(true);
	}

	public Editor getEditor() {
		return editor;
	}

	public ColorPicker getColorPicker() {
		return colorPicker;
	}

	public TopToolBar getTopToolBar() {
		return toolBar;
	}



}
