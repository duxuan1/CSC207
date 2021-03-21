package designpatterns.observer;

import java.util.ArrayList;

public class Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public void attach(Observer o) {
		this.observers.add(o);
	}

	public void detach(Observer o) {
		this.observers.remove(o);
	}

	public void notifyObservers() {
		for (Observer o : this.observers) {
			o.update();
		}
	}
}
