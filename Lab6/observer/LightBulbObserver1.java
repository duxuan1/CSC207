package designpatterns.observer;

public class LightBulbObserver1 implements Observer {
	
	@Override
	public void update() {
		System.out.println("Something Changed");
	}
}
