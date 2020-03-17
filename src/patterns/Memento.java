package patterns;



public class Memento {
    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
  
    }

    public void restore() {
        editor.restore(backup);
    }
}