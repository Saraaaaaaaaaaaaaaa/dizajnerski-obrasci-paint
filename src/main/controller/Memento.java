package main.controller;

import java.io.Serializable;

import main.model.Editor;

public class Memento  implements Serializable{
    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }
}