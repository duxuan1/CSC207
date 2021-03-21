package designpatterns.singleton;

// http://www.oodesign.com/singleton-pattern.html 
public class Singleton {

	private static Singleton instance = null;
	
	private String colour;

	private Singleton() {
		this.colour = "red";
	} // Notice this is private!!

	public static synchronized Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		
		return instance;
	}

	public String getColour() {
		return this.colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
}
