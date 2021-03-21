package designpatterns.factory;

// An alternative implementation of Food, 
// using an interface rather than a class
public interface Food2 {
	
	public String eat();

	public int getCalories();

	public String getName();
}
