package designpatterns.iterator;

public class AllIntegersMain {

	public static void main(String[] args) {
		
		// Demo using for loop
		for (int i : new AllIntegers()) {
			System.out.println(i);
		}
	}
}
