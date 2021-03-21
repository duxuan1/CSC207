package designpatterns.factory;

import java.util.Scanner;

public class ClientMain {
	
	public static void main(String[] args) {
		
		FoodFactory factory = new FoodFactory();
		
		Food food1 = factory.createProduct("Burger");
		System.out.println(food1.getClass().getName());
		System.out.println(food1.getName() + ", Calories: " + food1.getCalories());
//		
		Food food2 = factory.createProduct("Salad");
		System.out.println(food2.getClass().getName());
		System.out.println(food2.getName() + ", Calories: " + food2.getCalories());
//		
		Food food3 = factory.createProduct("Coke");
		System.out.println(food3.getClass().getName());
		System.out.println(food3.getName() + ", Calories: " + food3.getCalories());
//		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("\nWhat food would like to order?");
			String order = scan.nextLine();
			Food food = factory.createProduct(order);
			if (food != null) {
				System.out.println("Here you go: " + food.getName() + ", " + food.getCalories() + " calories.");
				System.out.println("Eating: " + food.eat());
			}
			else {
				System.out.println("Sorry, we don't have this on our menu.");
			}
		}
	}
	
}