package shapes;

/**
 * Since this class is not abstract, we must implement ALL the
 * abstract methods in the Shape class, as well as override some 
 * of the non-abstract methods in the base class (if something 
 * different needs to be done).
 * 
 * Another option: this class can also be abstract if necessary.
 */
public class Circle extends Shape {
	
	private double radius;

	public Circle(int x, int y, double r) {
		super(x, y);
		this.radius = r;
	}
	
	public double getArea() {
		return 3.14 * this.radius * this.radius;
	}
	
	public double getPerimeter() {
		return 3.14 * 2 * this.radius;
	}
	
	public void setRadius(double r) {
		this.radius = r;
	}
	
	@Override
	public String toString() {
		return "A Circle at (" + this.getX() + ", " + this.getY() + ") with radius " + this.radius;
	}
}
