package designpatterns.factory;

public class Burger2 implements Food2 {
	
	// Burger2 and Pizza2 share so much code, might as well make FoodInterface
	// a class Food, and then subclass it.
	private int calories;
	private String name;

	public Burger2() {
		this.name = "Burger2";
		this.calories = 780;
	}

	@Override
	public String eat() {
		return "bite chew chew";
	}

	@Override
	public int getCalories() {
		return this.calories;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
