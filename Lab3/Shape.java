package shapes;

/**
 * Abstract class that describes what every shape has in common.
 * But this is NOT any concrete shape, so cannot be instantiated.
 */
public abstract class Shape {
	
	// the location of the shape, every shape has a location
	// set to protected because we want subclasses to access this.x and this.y
	protected int x;
	protected int y;
	
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Abstract methods
	// Every shape must have these methods, but we cannot implement
	// the methods here because we don't have a concrete shape.
	public abstract double getArea();
	public abstract double getPerimeter();
	
	// These are NOT abstract methods, because we know how to implement them.
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "A Shape at (" + this.getX() + ", " + this.getY() + ")";
	}
}
