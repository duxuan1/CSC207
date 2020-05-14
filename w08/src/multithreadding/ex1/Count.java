package multithreadding.ex1;

/**
 * We will add threads, one step at a time. Each thread has it own local
 * registers, own stack.
 *
 * Threads may or may not be tied to a processor, they may share a processor.
 * When you run this code, it starts with one main thread.
 **/

public class Count {
	public static void main(String[] args) {
		long max = 100000000000L;
		Worker w1 = new Worker(0, max / 2, "W1");
		Worker w2 = new Worker(max / 2, max, "W2");

		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);

		t1.start();
		t2.start();

		System.out.println("Finished Starting Threads"); // 3 thread running

		try {
			t1.join(); // Wait for the thread to finish
			t2.join();
		} catch (InterruptedException e) {
		}

		long sum = w1.sum + w2.sum;
		System.out.println("Sum = " + sum);
	}
}
