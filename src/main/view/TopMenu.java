package main.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

import main.controller.CustomPaintComponent;
import main.controller.listeners.SaveLogsListener;

public class TopMenu extends JMenuBar  {
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem open;
	private JMenu saveAs;
	private JMenuItem saveAstxt;
	private JMenuItem savelogs;	
	private JMenuItem select;
	private JMenuItem scale;
	private JMenuItem colorPicker;
	private CustomPaintComponent painter;
	public TopMenu(CustomPaintComponent painter) {
		this.painter = painter;
		setMaximumSize(new Dimension(1200, 30));
		addEventLiseners();
		add(file);
		add(edit);
		edit.add(select);
		edit.add(scale);
		edit.add(colorPicker);
		file.add(open);
		file.addSeparator();
		file.add(saveAs);
		
	}

	private void addEventLiseners(){
		select = new JMenuItem(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				painter.setSelectedShape("SELECT");
				painter.changeCursorToMove();
				
			}
		});
		scale = new JMenuItem(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				painter.setSelectedShape("SCALE");				
			}
		});
		colorPicker = new JMenuItem(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				painter.setSelectedShape("COLORING");
				
			}
		});

		saveAs = new JMenu(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		saveAstxt  = new JMenuItem(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			painter.getEditor().saveHistory();
			}
		});
		
		
		savelogs = new JMenuItem(new SaveLogsListener(painter));
		
		saveAstxt.setText("text file");
		savelogs.setText("logs");
		saveAs.add(saveAstxt);
		saveAs.add(savelogs);
		open = new JMenuItem(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				painter.getEditor().loadHistory();
				painter.repaint();
			}
		});
		saveAs.setText("Save as");
		open.setText("Open");
		select.setText("Select shape");
		scale.setText("Scale shape");
		colorPicker.setText("Color pick");
		
	}
}
