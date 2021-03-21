import java.util.Arrays; // import so that we can use Arrays
import java.util.HashMap; // import so that we can use HashMap

public class ArrayMapCtrlFunc {
	public static void main(String args[]) {

		// if-statement
		int n = 7, x = 10;
		if (x > n) {
			int temp = x; // BLOCK
			x = n;
			n = temp;
		} else {
			x = 0;
			n = 100; // BLOCK
		}
//		
		System.out.println("x=" + x + " n=" + n);

		// Arrays
		// - all elements have to have the same type (unlike Python list)
		// - size of the array is fixed
		// See UseBalloon.java for the use of another more flexible type: ArrayList
		int[] myArray = new int[3];
		myArray[0] = 207;
		System.out.println(Arrays.toString(myArray));

		// range(start, end)
		for (int i = 0; i < myArray.length; i++) {
			System.out.println(myArray[i]);
		}

//		int i = 0;
//		while (i < myArray.length) {
//			System.out.println(myArray[i]);
//			i++;
//		}

		for (int item : myArray) {
			System.out.println(item);
		}

		n = 5;
		int m = mystery(n);
		m = mystery2(n);
		System.out.println("m=" + m + " n=" + n);

		// HashMap is basically Python dictionary
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		for (int i = 0; i < 5; i++) {
			hm.put(i, "This is value " + i);
		}
		for (int k : hm.keySet()) {
			System.out.println(k + ": " + hm.get(k));
		}
		for (String v : hm.values()) {
			System.out.println(v);
		}
		for (HashMap.Entry<Integer, String> e : hm.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}

	} // end of main function

	// Define a function outside the main function
	static int mystery(int n) {
		// while and break
		int x = 0;
		int i = 1;
		while (i <= n) {
			x = x + i;
			i = i + 1;
		}
		n = 0;
		return (x); // evaluate, assign back, return control
	}

	static int mystery2(int i) {
		int retVal = 1;
		if (i == 0) {
			// Solve simple problem
			retVal = 1;
		} else {
			// Complex problem solved using simpler ones
			retVal = i * mystery2(i - 1);
		}
		return retVal;
	}
}
