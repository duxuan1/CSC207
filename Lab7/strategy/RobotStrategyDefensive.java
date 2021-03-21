package designpatterns.strategy;

public class RobotStrategyDefensive implements RobotStrategy {

	@Override
	public String nextCommand() {
		return "defense";
	}
}
