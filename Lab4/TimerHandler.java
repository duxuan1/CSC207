package guifx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class TimerHandler implements EventHandler<ActionEvent> {

	private int v = 0;
	private String name;
	private Label label;

	public TimerHandler(String name, Label label) {
		this.name = name;
		this.label = label;
		this.label.setText(name + ": " + v);
	}

	@Override
	public void handle(ActionEvent event) {
		v++;
		System.out.println(this.name + ": " + v);
		this.label.setText(this.name + ": " + v);
	}

}
