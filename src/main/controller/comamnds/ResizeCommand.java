package main.controller.comamnds;



import main.model.Editor;
import main.model.shapes.Point;
import main.model.shapes.Shape;

public class ResizeCommand implements Command{
	private Editor editor;

	private Point start, end;
	
	public ResizeCommand(Editor editor) {
		this.editor = editor;
	}
		

	@Override
	public String getName() {
		return "RESIZE SHAPE on positions -> start " +start + " end "+ end ;
		}
	public void start(int x, int y) {
		start = new Point(x, y);
		
	}
	public void scale(int x, int y) {
		for(Shape target : editor.getSelected()) 
			target.scaleTo( x, y);
	
	}
	public void stop(int x, int y) {
		end = new Point(x, y);
	}

	@Override
	public void execute() {	
		
		for(Shape target : editor.getSelected()) { 
			target.scaleTo(end.getX() , end.getY());
			editor.log(getName());
		}
	}

	@Override
    public boolean hasTarget() {
    	return editor.getSelected().size() == 0 ? false : true;
    }
    




}

