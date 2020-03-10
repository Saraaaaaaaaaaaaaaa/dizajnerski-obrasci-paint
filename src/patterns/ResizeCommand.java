package patterns;

public class ResizeCommand implements Command{
	private Editor editor;
	private int startX, startY;
	private int endX, endY;
	
	public ResizeCommand(Editor editor) {
		this.editor = editor;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public void start(int x, int y) {
		startX = x;
		startY = y;
		
	}
	public void scale(int x, int y) {
		Shape target = editor.getShapes().getSelected().get(0);
		target.scaleTo( x, y);
	
	}
	public void stop(int x, int y) {
		endX = x;
		endY = y;
	}

	@Override
	public void execute() {
		Shape target = editor.getShapes().getSelected().get(0);
		target.scaleTo(endX, endY);
		
	}

}

