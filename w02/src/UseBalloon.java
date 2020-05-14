import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class UseBalloon {

	public static void main(String args[]) {
		Balloon b; // b is a reference to Balloon, none exist yet
		b = new Balloon("red"); // b is the reference storing the memory address of the red Balloon
		b.addAir(10);
		System.out.println(b);
		b.addAir(50);
		System.out.println(b);
		b.addAir(50);
		System.out.println(b);
		b.addAir(50);
		System.out.println(b);

		System.out.println("Another Balloon");
		b = new Balloon("blue", 50); // b is now storing the adress of the blue Balloon
		Balloon c = b; // c is another reference storing the address of the SAME blue Balloon!
		b.addAir(10);
		c.addAir(15);
		System.out.println(c);

		int p1 = 42;
		func(p1);
		System.out.println(p1); // What's the value of p1?

		Balloon p2 = new Balloon("red");
		func2(p2);
		System.out.println(p2); // What's the amount and color for p2?
		// Read Larry's Lecture 2 slides for explanations

		// Russian Roulette with Balloons
		// ArrayList, unlike Array, is not fix-sized. It's more like Python list
		ArrayList<Balloon> balloons = new ArrayList<Balloon>();

		Scanner scan = new Scanner(System.in);
		System.out.println("How many balloons?");
		int numBalloons = Integer.parseInt(scan.nextLine());
		System.out.println("What colour?");
		String colour = scan.nextLine();

		// Initialization
		Random rand = new Random();
		for (int i = 0; i < numBalloons; i++) {
			int cap = 80 + rand.nextInt(80);
			balloons.add(new Balloon(colour, cap));
		}

		while (!balloons.isEmpty()) {
			System.out.println("Here are the balloons:");
			printBalloons(balloons);
			System.out.println("\nNext player, Type your name and hit enter");
			String name = scan.nextLine();
			int idx = rand.nextInt(balloons.size());
			int amnt = rand.nextInt(120);
			System.out.println("Adding " + amnt + " to Balloon #" + (idx + 1) + "...\n");
			balloons.get(idx).addAir(amnt);
			if (balloons.get(idx).isPopped()) {
				System.out.println(name + " is dead, popped balloon removed.\n");
				balloons.remove(idx);
			}
		}

		scan.close(); // close the scanner

		System.out.println("GAME OVER!");
	}

	public static void func(int x) {
		x = 3;
		// Read Larry's Lecture 2 slides for explanation
	}

	public static void func2(Balloon b) {
		b.addAir(42);
		b = new Balloon("yellow");
		// Read Larry's Lecture 2 slides for explanation
	}

	public static void printBalloons(ArrayList<Balloon> balloons) {
		for (int i = 0; i < balloons.size(); i++) {
			System.out.println("Balloon #" + (i + 1) + ": " + balloons.get(i));
		}
	}

}