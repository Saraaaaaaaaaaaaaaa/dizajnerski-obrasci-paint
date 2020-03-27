package main.controller.listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.CustomPaintComponent;

public class DeleteButtonListener extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private CustomPaintComponent painter;

	public  DeleteButtonListener(CustomPaintComponent painter) {
		this.painter = painter;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		painter.getEditor().deleteSelectedShapes();	
		painter.notifyManager("ENABLE BUTTONS", "DISABLE");
		painter.setSelectedShape("SELECT");
		painter.repaint();
	}

}
