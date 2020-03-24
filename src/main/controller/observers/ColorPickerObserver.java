package main.controller.observers;



import main.controller.Observer;
import main.view.TopToolBar;

public class ColorPickerObserver implements Observer{

	private TopToolBar view;
	
	public  ColorPickerObserver(TopToolBar target) {
		this.view = target;
	}


	@Override
	public void update() {
			System.out.println("OBSEERVER SYSIO");		
	}


	@Override
	public void update(String option) {
		// TODO Auto-generated method stub
		
	}

	

}
