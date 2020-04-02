package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
				      .addComponent(paintComponent))
				);
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		    		  .addComponent(toolBar)		    		  
		           .addComponent(paintComponent)
		);
		setJMenuBar(menu);
	
		setFocusable( true );
		attachKeyboardListeners();
		attachMouseListeners();
		add(frame);
		pack();		
	    setVisible(true);
	}
	private void attachMouseListeners() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
					System.out.println(hasFocus());
			}
		});
		
	}
	private void attachKeyboardListeners() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				System.out.println("PRESES: " +e );
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_Z:
						editor.undo();
						break;
					case KeyEvent.VK_R:
						editor.redo();
						break;
					}
				}
			}
		});
	}
	public Editor getEditor() {
		return editor;
	}

	public TopToolBar getTopToolBar() {
	return toolBar;
	}

	public ColorPicker getColorPicker() {
		return toolBar.getColorPicker();
	}

	


}
