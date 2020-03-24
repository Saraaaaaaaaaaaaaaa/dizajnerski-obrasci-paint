package main.controller;

import java.util.HashMap;

public class EventManeger {

	private HashMap<String, Observer> listeners = new HashMap<>();
	
	public void subscribe(String listenerName, Observer lisener) {
		listeners.put(listenerName, lisener);
		}
	
	public void unsubscribe(String listenerName, Observer lisener) {
		listeners.remove(listenerName);
	}
	
	public void notifyObservers(){
		listeners.forEach((k, v) -> v.update());
	}
	
	
	public void notifyObserver(String name, String option){
		listeners.get(name).update(option);
	}
	
}
