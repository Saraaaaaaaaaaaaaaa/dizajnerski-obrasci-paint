package main;

import main.view.MainWindow;

public class Starter {
	//sa njime se obezbedjuje da klasa ima samo jednu instancu
	private static MainWindow singleton = null;
	// zabranjuje instanciranje iz drugih klasa
	public Starter() {
		if( singleton == null) {
			  singleton = new MainWindow();		 		  
			}
	}
	public static void main(String[] args) {
		
		Starter app = new Starter();
	}

	
}
