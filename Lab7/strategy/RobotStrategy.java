package designpatterns.strategy;

// generic strategy interface
public interface RobotStrategy {

	// returns a string which is the next command
	public String nextCommand();
}
