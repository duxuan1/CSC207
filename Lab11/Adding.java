package floating;

public class Adding {

	public static void main(String[] args) {
		
		double x = 1.5;
		double x2 = 0.5;
		double sum = x + x2;
		System.out.println(sum == 2.0);

		x = 0.1;
		sum = x + x;
		System.out.println(sum == 0.2);

		x = 0.1;
		sum = x + x + x;
		System.out.println(sum == 0.3);
		System.out.println("sum = " + sum);

		double big = 1.0;
		double small = 1.0e-6; // 0.000001
		double sum1 = small + small + big;
		double sum2 = big + small + small;
		System.out.println("sum1 == sum2: " + (sum1 == sum2));
		System.out.println("sum1 = " + sum1);
		System.out.println("sum2 = " + sum2);

		double v = 0.1;
		while (v != 1.0) {
			System.out.println(v);
			v = v + 0.1;
		}
	}
}
