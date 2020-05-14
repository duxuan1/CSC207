package multithreadding.ex3;

class Sum {
	private long c = 0;

	public void add(long amount) {
		c = c + amount;
	}

	public long value() {
		return c;
	}
}