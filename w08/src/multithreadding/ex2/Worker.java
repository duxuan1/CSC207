package multithreadding.ex2;

class Worker implements Runnable {
	long start, end, sum;

	public Worker(long start, long end) {
		this.start = start;
		this.end = end;
		this.sum = 0;
	}

	public void run() {
		for (long i = this.start; i < this.end; i++) {
			this.sum += i;
		}
	}
}