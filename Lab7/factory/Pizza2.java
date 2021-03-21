package designpatterns.factory;

// Burger2 and Pizza2 share so much code, might as well make FoodInterface
// a class Food, and then subclass it.
public class Pizza2 implements Food2 {

	private int calories;
	private String name;

	public Pizza2() {
		this.name = "Pizza2";
		this.calories = 300;
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
