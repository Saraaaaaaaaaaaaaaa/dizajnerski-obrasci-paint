package main.controller.listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.CustomPaintComponent;

public class ModifyButtonListener extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private CustomPaintComponent painter;

	public  ModifyButtonListener(CustomPaintComponent painter) {
		this.painter = painter;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		painter.changeCursorToModificatior();
		painter.repaint();
	}

}
