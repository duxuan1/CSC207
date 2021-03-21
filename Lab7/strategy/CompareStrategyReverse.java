package designpatterns.strategy;

public class CompareStrategyReverse implements CompareStrategy {

	@Override
	public int compare(String s1, String s2) {
		if (s1.compareTo(s2) < 0) {
			return CompareStrategy.GREATER;
		}
		if (s1.compareTo(s2) > 0) {
			return CompareStrategy.LESS;
		}
		return CompareStrategy.EQUAL;
	}
}
