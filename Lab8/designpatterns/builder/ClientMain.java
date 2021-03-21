package designpatterns.builder;

import java.util.ArrayList;

public class ClientMain {

	public static void main(String[] args) {

		PizzaBuilder builder = new PizzaBuilder("Pineroni");
		builder.addExtraCheese();
		builder.addPepperoni();
		builder.addPineapple();
		Pizza p0 = builder.getPizza();
		System.out.println(p0);
		
		PizzaBuilder hawaiianBuilder = new HawaiianPizzaBuilder();
		Pizza h0 = hawaiianBuilder.getPizza();
		System.out.println(h0);
		
		PizzaBuilder deluxeBuilder = new DeluxePizzaBuilder();
		Pizza d0 = deluxeBuilder.getPizza();
		System.out.println(d0);
//		
		// use the director to construct a bunch of pizza
		PizzaDirector director = new PizzaDirector();
		director.construct();
		ArrayList<Pizza> pizzas = director.getPizzas();

		for (Pizza p : pizzas) {
			System.out.println(p);
		}

		// free-style using the chain builder
		Pizza p1 = new PizzaChainBuilder("TheLarry")
				.addExtraCheese()
				.addExtraSauce()
				.addPepperoni()
				.getPizza();
		System.out.println(p1);

		Pizza p2 = new PizzaChainBuilder("TheFurkan")
				.addExtraSauce()
				.addTomato()
				.addPineapple()
				.getPizza();
		System.out.println(p2);

		Pizza p3 = new PizzaChainBuilder("TheArnold")
				.getPizza();
		System.out.println(p3);
	}
}