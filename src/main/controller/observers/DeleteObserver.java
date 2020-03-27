package main.controller.observers;

import main.controller.Observer;
import main.model.shapes.CompoundShape;
import main.view.TopToolBar;

public class DeleteObserver implements Observer {

	private TopToolBar view;

	public DeleteObserver(TopToolBar target) {
		this.view = target;
	}

	@Override
	public void update(String option) {
		if (view != null) {
			if (option.contains("ENABLE")) {
				if(option.contains("DELETE"))
					view.enableDeleteButton();
				if(option.contains("MODIFY"))
					view.enableModifyButton();}
			else if (option.contains("DISABLE")) {
				if(option.contains("DELETE"))
					view.disableDeleteButton();
				if(option.contains("MODIFY"))
					view.disableModifyButton();				
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
