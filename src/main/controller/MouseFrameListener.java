package main.controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.model.Point;
import main.view.MainWindow;

public class MouseFrameListener implements MouseListener {

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX() + "  " + e.getY());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
