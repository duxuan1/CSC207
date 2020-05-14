package multithreadding.ex4;

class Sum {
	private long c = 0;

	public synchronized void add(long amount) {
		c += amount;
	}

	public long value() {
		return c;
	}
}
