package patterns;


import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.loadShapes(
                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                )
        );
    }
}