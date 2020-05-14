package designpatterns.iterator;

import java.util.Iterator;

// http://www.oodesign.com/iterator-pattern.html
// But we implement the Java specific 
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
// https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html

public class RangeMain {
	// Exercises:
	// An https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.htmlterator
	// like Range(min, max, step)
	// An iterator for all integers
	// An iterator for all pairs of natural numbers

	public static void main(String[] args) {

		// Want to do something like...
		// Range r = new Range(0,10,1);

		// Demo using API
		Range r0 = new Range(1, 10, 2);
		Iterator<Integer> ri = (RangeIterator) r0.iterator();
		while (ri.hasNext()) {
			int j = ri.next();
			System.out.println(j);
		}

		ri = r0.iterator();
		while (ri.hasNext()) {
			int j = ri.next();
			System.out.println(j);
		}

		Range r = new Range(0, 10, 1);
		for (int i : r) {
			System.out.println(i);
		}

		for (int i : r) {
			for (int j : r) {
				System.out.println(i + " " + j);
			}
		}
	}
}
