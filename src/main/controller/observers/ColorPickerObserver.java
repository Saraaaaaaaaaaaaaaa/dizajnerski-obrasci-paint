package main.controller.observers;



import main.controller.Observer;
import main.view.TopToolBar;

public class ColorPickerObserver implements Observer{

	private Object view;
	private String type;
	
	public  ColorPickerObserver(Object target, String type) {
		this.view = target;
		this.type = type;
	}


	@Override
	public void update() {
	}


	@Override
	public void update(String option) {
		if( view instanceof TopToolBar) {
			if( type.contains("FILL"))				
				((TopToolBar)view).setCurrentFillColor(option);
			else if(type.contains("LINE"))
				((TopToolBar)view).setCurrentLineColor(option);
			
			((TopToolBar)view).updateView();
		}
	}

	

}
