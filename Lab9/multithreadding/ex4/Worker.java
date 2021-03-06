package multithreadding.ex4;

class Worker implements Runnable {
	long start, end;
	Sum sum;

	public Worker(long start, long end, Sum sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
	}

	public void run() {
		System.out.println("Thread running: " + this.start + " to " + this.end);
		for (long i = this.start; i < this.end; i++) {
			sum.add(i);
		}
	}
}
