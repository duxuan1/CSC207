package shapes;

public class Square extends Shape {
	
	private int sideLength;
	
	public Square(int x, int y, int s) {
		super(x, y);
		this.sideLength = s;
	}
	
	public double getArea() {
		return sideLength * sideLength;
	}
	
	public double getPerimeter() {
		return 4 * sideLength;
	}
	
	public void setSideLength(int s) {
		this.sideLength = s;
	}
	
	@Override
	public String toString() {
		return "A Square at (" + this.getX() + ", " + this.getY() + ") with side length " + this.sideLength;
	}
}