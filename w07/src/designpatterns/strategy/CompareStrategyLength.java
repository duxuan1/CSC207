package designpatterns.strategy;

public class CompareStrategyLength implements CompareStrategy {

	@Override
	public int compare(String s1, String s2) {
		if (s1.length() < s2.length()) {
			return CompareStrategy.LESS;
		} else if (s1.length() > s2.length()) {
			return CompareStrategy.GREATER;
		}
		return CompareStrategy.EQUAL;
	}
}
