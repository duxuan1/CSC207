package multithreadding.ex1;

class Worker implements Runnable {

	long start, end, sum;
	String name;

	public Worker(long start, long end, String name) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.sum = 0;
	}

	public void run() {
		System.out.println("Thread running: " + this.start + " to " + this.end);
		for (long i = this.start; i < this.end; i++) {
			this.sum += i;
//			if (i % 10000 == 0)
//				System.out.println(name + " " + sum);
		}
	}
}