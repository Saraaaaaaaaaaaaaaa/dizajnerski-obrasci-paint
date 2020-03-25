package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
//import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.swing.JPanel;

import main.controller.CustomPaintComponent;
import main.model.Editor;

public class MainWindow  extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel frame;
	private  CustomPaintComponent paintComponent;
	private Editor editor ;
	private ColorPicker colorPicker ;
    private TopToolBar toolBar;
    private TopMenu menu;
    
	public MainWindow() {
			
		setTitle("Projekat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 500));	
		
		editor = new Editor();	
		paintComponent = new CustomPaintComponent(this); 
		menu = new TopMenu(paintComponent);
		toolBar = new TopToolBar( getPreferredSize().width, paintComponent );
		paintComponent.setBackground(new Color(255,5,5));
		colorPicker = new ColorPicker(toolBar);
		toolBar.add(colorPicker);
		paintComponent.setSource(this);	
		
		frame = new JPanel();
		add(toolBar);  
		GroupLayout layout = new GroupLayout(frame);
		frame.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						      .addComponent(toolBar)
				              .addComponent(colorPicker)
				      .addComponent(paintComponent))
				);
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		    		  .addComponent(toolBar)
		    		  .addComponent(colorPicker)
		           .addComponent(paintComponent)
		);
		setJMenuBar(menu);
	
	
		add(frame);
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
