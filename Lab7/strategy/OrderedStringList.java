package designpatterns.strategy;

import java.util.ArrayList;

public class OrderedStringList {
	
	private ArrayList<String> list = new ArrayList<String>();
	private CompareStrategy strategy;

	// use the normal compare strategy be default
	public OrderedStringList() {
		this(new CompareStrategyNormal());
	}

	// create a list with a customized strategy
	public OrderedStringList(CompareStrategy strategy) {
		this.strategy = strategy;
	}

	// add one string to the list, keep the list ordered
	public void add(String s) {
		for (int i = 0; i < this.list.size(); i++) {
			if (strategy.compare(s, this.list.get(i)) == CompareStrategy.LESS) {
				this.list.add(i, s);
				return;
			}
		}
		// if s is larger than everything in the list, append at the end
		this.list.add(s);
	}

	// add all an Array of strings to the list
	public void addAll(String[] stuff) {
		for (String e : stuff) {
			this.add(e);
		}
	}

	// a static implementation of of add all
	public static void staticAddAll(String[] stuff, OrderedStringList ol) {
		for (String e : stuff) {
			ol.add(e);
		}
	}

	public String toString() {
		String s = "[";
		for (String e : this.list) {
			s += (e + ", ");
		}
		return s + "]";
	}

	public static void main(String[] args) {
		
		String[] stuff = { "this", "that", "something", "a", "another", "them" };
		
		OrderedStringList o1 = new OrderedStringList();
		OrderedStringList o2 = new OrderedStringList(new CompareStrategyReverse());
		OrderedStringList o3 = new OrderedStringList(new CompareStrategyLength());

		o1.addAll(stuff);
		o2.addAll(stuff);
		OrderedStringList.staticAddAll(stuff, o3);

		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);
	}
}
