package designpatterns.strategy;

public class RobotStrategyAggressive implements RobotStrategy {

	@Override
	public String nextCommand() {
		return "attack";
	}
}
