package main.controller.comamnds;

import main.model.Editor;
import main.model.shapes.Shape;

public class ChangeOrderCommand implements Command{

	private Editor editor;
	private int oldIndex, newIndex;
	private Shape target;
	
	public ChangeOrderCommand(Editor editor, Shape target, int index) {
		this.editor = editor;
		this.target = target;
		this.newIndex = index;
	}
	
	@Override
	public String getName() {
		return "CHANGED TO INDEX -> " + newIndex;
	}

	@Override
	public void execute() {
		this.oldIndex = editor.getShapes().indexOf(target) ;
		if( target != null && oldIndex != -1) {
			editor.getShapes().remove(target);
			editor.getShapes().add(this.newIndex, target);
		}
		
	}

	@Override
	public boolean hasTarget() {
		// TODO Auto-generated method stub
		return false;
	}

}
