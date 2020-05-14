
package multithreadding.ex2;

public class Count {
	public static void main(String[] args) {

		long max = 100000000000L;
		int numWorkers = 1;

		Worker[] w = new Worker[numWorkers];
		Thread[] t = new Thread[numWorkers];

		long workSize = max / numWorkers;
		long start = 0L, end = workSize;
		for (int i = 0; i < numWorkers; i++) {
			if (i == numWorkers - 1)
				end = max;
			w[i] = new Worker(start, end);

			t[i] = new Thread(w[i]);
			t[i].start();

			start = end;
			end += workSize;
		}

		System.out.println("Finished Starting Threads");

		long sum = 0;
		try {
			for (int i = 0; i < numWorkers; i++) {
				t[i].join();
				sum += w[i].sum;
			}

		} catch (InterruptedException e) {
		}

		System.out.println("Sum = " + sum);
	}
}
