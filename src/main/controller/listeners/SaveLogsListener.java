package main.controller.listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.CustomPaintComponent;

public class SaveLogsListener extends AbstractAction{

	CustomPaintComponent painter;
	public SaveLogsListener(CustomPaintComponent painter) {
		this.painter = painter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		painter.getEditor().saveLogs();
		
	}

}
