package main.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
//import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import main.controller.CustomPaintComponent;
import main.model.Editor;

public class MainWindow  extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private  CustomPaintComponent paintComponent;
	
    public TopToolBar toolBar;
    
	public MainWindow() {
			
		setTitle("Projekat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(700, 500));	
		Editor editor = new Editor();
		ColorPicker colorPicker = new ColorPicker();
		paintComponent =  new CustomPaintComponent(editor, colorPicker);
		toolBar = new TopToolBar( getPreferredSize().width, paintComponent );	  
		toolBar.add(colorPicker);
		add(toolBar);  
	
		paintComponent.setBackground(new Color(255,5,5));
		add(paintComponent);
	    
	    pack();		
	    setVisible(true);
	}
	

}
