package designpatterns.observer;

public class LightBulb extends Observable {
	
	public final static boolean ON = true;
	public final static boolean OFF = false;

	private boolean bulbStatus = OFF;

	public void turnOn() {
		if (this.bulbStatus != ON) {
			this.bulbStatus = ON;
			this.notifyObservers();
		}
	}

	public void turnOff() {
		if (this.bulbStatus != OFF) {
			this.bulbStatus = OFF;
			this.notifyObservers();
		}
	}

	public void flip() {
		this.bulbStatus = !this.bulbStatus;
		this.notifyObservers();
	}
}
