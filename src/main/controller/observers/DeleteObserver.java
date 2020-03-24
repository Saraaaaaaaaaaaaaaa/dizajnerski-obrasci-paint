package main.controller.observers;

import main.controller.Observer;
import main.view.TopToolBar;

public class DeleteObserver implements Observer{

	private TopToolBar view;
	
	public  DeleteObserver(TopToolBar target) {
		this.view = target;
	}
	


	@Override
	public void update(String option) {
		if(view != null) {
			if(option.contains("ENABLE")) 			
				view.enableDeleteButton();
			else if(option.contains("DISABLE"))
				view.disableDeleteButton();
		}		
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	

}
