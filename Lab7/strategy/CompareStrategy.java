package designpatterns.strategy;

public interface CompareStrategy {

	public static final int LESS = -1;
	public static final int EQUAL = 0;
	public static final int GREATER = 1;

	// this method returns LESS if s1 < s2, EQUAL if s1 == s2
	// and GREATER if s1 > s2
	public int compare(String s1, String s2);
}
