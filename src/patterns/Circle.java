package patterns;

import java.awt.*;

public class Circle extends BaseShape {
    private int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
    
    }

	@Override
	public void scaleTo(int x, int y) {
			this.radius = (int) Math.sqrt(( x - super.x) * (x-super.x) + (y - super.y)*(y- super.y));
			this.radius = (int) ((int)this.radius*Math.sqrt(2)/4);
			
		
			
	}
}