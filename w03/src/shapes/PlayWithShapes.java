package shapes;

import java.util.ArrayList;

public class PlayWithShapes {
	public static void main(String args[]) {
		//Shape s = new Shape(1, 2); // Cannot instantiate an abstract class!
		
		Circle c1 = new Circle(0, 0, 3);
		Shape c2 = new Circle(1, 1, 5); // This is OK!
		
		//c2.setRadius(6); // NOT OK
		((Circle)c2).setRadius(6); // This is OK
		// The casting is safe only if you know that c2 is indeed a Circle
		
		Rectangle r1 = new Rectangle(1, 2, 3, 4);
		Square s1 = new Square(5, 6, 8);
		
		ArrayList<Shape> array = new ArrayList<Shape>();
		
		// Each entry of the list can be any subclass of Shape
		array.add(c1);
		array.add(c2);
		array.add(r1);
		array.add(s1);
		
		drawShapes(array);
		
		
		ArrayList<Circle> circle_array = new ArrayList<Circle>();
		
		circle_array.add(new Circle(1, 2, 3));
		circle_array.add(new Circle(4, 5, 6));
		
		// The following line causes compiler error because ArrayList<Circle> is NOT 
		// a sub-type of ArrayList<Shape>, therefore auto-conversion cannot happen
		//drawShapes(circle_array);
		
		// This works, what's the difference?
		//drawShapesGeneric(circle_array);
		
	}
	
	static void drawShapes(ArrayList<Shape> lst) {
		
		for (Shape s: lst) {
			System.out.println(s);
		}
	}
	
	// Use wildcard "?", this function takes an ArrayList of unknown type 
	// that is a sub-type of Shape
	static void drawShapesGeneric(ArrayList<? extends Shape> lst) {
		
		for (Shape s: lst) {
			System.out.println(s);
		}
	}
}
