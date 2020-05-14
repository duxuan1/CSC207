package floating;

public class LoopCounter {

	public static void main(String[] args) {

		// Here, the actual quantity added with each iteration
		// is slightly larger than 0.1
		for (float x = 0.1f; x != 1.0f; x += 0.1f) {
			System.out.println(x);
		}
		
		for (float x = 0.1f; x <= 1.0f; x += 0.1f) {
			System.out.println(x);
		} // not getting exactly what you expect

		System.out.println("====");
		// Solution: use an integer loop counter from which
		// the desired floating point is derived
		for (int count = 1; count <= 10; count += 1) {
			float x = count / 10.0f;
			System.out.println(x);
		}
	}
}
