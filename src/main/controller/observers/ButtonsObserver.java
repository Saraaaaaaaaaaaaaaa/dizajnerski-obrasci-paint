package main.controller.observers;

import main.controller.Observer;
import main.model.shapes.CompoundShape;
import main.view.TopToolBar;

public class ButtonsObserver implements Observer {

	private TopToolBar view;

	public ButtonsObserver(TopToolBar target) {
		this.view = target;
	}

	@Override
	public void update(String option) {
		if (view != null) {
			if (option.contains("ENABLE")) {
				if(option.contains("DELETE"))
					view.setButtonStatusByName("DELETE", true);
				if(option.contains("MODIFY"))
					view.setButtonStatusByName("MODIFY", true);
				if(option.contains("UNDO"))
					view.setButtonStatusByName("UNDO", true);
				if(option.contains("REDO"))
					view.setButtonStatusByName("REDO", true);				
			}			
			else if (option.contains("DISABLE")) {
				if(option.contains("DELETE"))
					view.setButtonStatusByName("DELETE", false);
				if(option.contains("MODIFY"))
					view.setButtonStatusByName("MODIFY", false);
				if(option.contains("UNDO"))
					view.setButtonStatusByName("UNDO", false);
				if(option.contains("REDO"))
					view.setButtonStatusByName("REDO", false);	
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
