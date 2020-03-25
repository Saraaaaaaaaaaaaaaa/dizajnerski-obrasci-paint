package main.controller.observers;



import main.controller.Observer;
import main.view.TopToolBar;

public class ColorPickerObserver implements Observer{

	private Object view;
	
	public  ColorPickerObserver(Object target) {
		this.view = target;
	}


	@Override
	public void update() {
			System.out.println("OBSEERVER SYSIO");		
	}


	@Override
	public void update(String option) {
		System.out.println("SELECTED COLOR " + option);				
	}

	

}
