package main.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.controller.Memento;
import main.controller.comamnds.Command;
import main.model.shapes.Shape;

public class History implements Serializable{
	private List<Pair> history = new ArrayList<Pair>();
	private int virtualSize = 0;

	private class Pair implements Serializable {

		private static final long serialVersionUID = 2L;
		Command command;
		Memento memento;

		Pair(Command c, Memento m) {
			command = c;
			memento = m;
		}

		private Command getCommand() {
			return command;
		}

		private Memento getMemento() {
			return memento;
		}
	}

	public void push(Command c, Memento m) {
		if (virtualSize != history.size() && virtualSize > 0) {
			history = history.subList(0, virtualSize - 1);
		}
		history.add(new Pair(c, m));
		virtualSize = history.size();
	}

	public boolean undo() {
		Pair pair = getUndo();
		if (pair == null) {
			return false;
		}
		System.out.println("Undoing: " + pair.getCommand().getName());
		pair.getMemento().restore();
		return true;
	}

	public boolean redo() {
		Pair pair = getRedo();
		if (pair == null) {
			return false;
		}
		System.out.println("Redoing: " + pair.getCommand().getName());
		pair.getMemento().restore();
		pair.getCommand().execute();
		return true;
	}

	private Pair getUndo() {
		if (virtualSize == 0) {
			return null;
		}
		virtualSize = Math.max(0, virtualSize - 1);
		return history.get(virtualSize);
	}

	private Pair getRedo() {
		if (virtualSize == history.size()) {
			return null;
		}
		virtualSize = Math.min(history.size(), virtualSize + 1);
		return history.get(virtualSize - 1);
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("history.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);

			oos.writeObject(this.history);
			oos.close();

		} catch (IOException e) {
		}

	}

	public void load() {
		try {
			FileInputStream fileIn = new FileInputStream("history.txt");
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			history.clear();
			this.history = (List<Pair>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void iterateOver() {
		for(Pair pair: history)
		{
			pair.command.execute();
			System.out.println(pair.command.getName());
		}
		
		
	}
}