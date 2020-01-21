package main;

import main.view.MainWindow;

public class Starter {

	private static MainWindow singleton = null;
	public static void main(String[] args) {
		
		if( singleton == null) {
		  singleton = new MainWindow();
		  
		}
	}

}
