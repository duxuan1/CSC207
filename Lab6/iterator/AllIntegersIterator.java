package designpatterns.iterator;

import java.util.Iterator;

public class AllIntegersIterator implements Iterator<Integer> {

	private int current = 0;

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Integer next() {
		int previous = this.current;
//		this.current++; // this only gives the non-negative integers
		if (this.current <= 0)
			this.current = -this.current + 1;
		else
			this.current = -this.current;
		return previous;
	}
}
