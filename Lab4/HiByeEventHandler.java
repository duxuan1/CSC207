package guifx;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HiByeEventHandler implements EventHandler<ActionEvent> {

	private TextField txt;
	private int count;

	public HiByeEventHandler() {
		this.count = 0;
	}

	// Why passing and remembering the reference to the text field?
	public HiByeEventHandler(TextField txt) {
		this.txt = txt;
		this.count = 0;
	}

	public void handle(ActionEvent event) {
		
		++this.count;
		
		// Note how to get the button's text from the event object.
		String msg = ((Button) (event.getSource())).getText() + " pressed " + this.count + " times";
		
		// The handler can manipulate the text field 
		// because it keeps the reference to it
		this.txt.setText(msg);
		return;
	}
}
