package multithreadding.ex0;

public class Count {
	public static void main(String[] args) {
		long sum = 0;
		long max = 10000000000L;
		System.out.println("Computing...");
		for (long i = 0; i < max; i++) {
			sum += i;
		}
		System.out.println("Sum=" + sum);
	}
}
