package designpatterns.command;

// a generic balloon command
public interface BalloonCommand {
	
	void execute();
	
	// execute can also take an argument, e.g., execute(args)
}
