package multithreadding.ex4;

public class Count {
	public static void main(String[] args) {

		long max = 10000000000L;
		int numWorkers = 4;

		Worker[] w = new Worker[numWorkers];
		Thread[] t = new Thread[numWorkers];

		Sum sum = new Sum();
		long workSize = max / numWorkers;
		long start = 0L, end = workSize;
		for (int i = 0; i < numWorkers; i++) {
			if (i == numWorkers - 1)
				end = max;
			w[i] = new Worker(start, end, sum);

			t[i] = new Thread(w[i]);
			t[i].start();

			start = end;
			end += workSize;
		}

		System.out.println("Finished Starting Threads"); // 3 thread running
		try {
			for (int i = 0; i < numWorkers; i++) {
				t[i].join();
			}

		} catch (InterruptedException e) {
		}

		System.out.println("Sum = " + sum.value());
	}
}
