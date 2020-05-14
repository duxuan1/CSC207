package designpatterns.strategy;

public class CompareStrategyNormal implements CompareStrategy {

	@Override
	public int compare(String s1, String s2) {
		if (s1.compareTo(s2) < 0) {
			return CompareStrategy.LESS;
		} else if (s1.compareTo(s2) > 0) {
			return CompareStrategy.GREATER;
		}
		return CompareStrategy.EQUAL;
	}
}
