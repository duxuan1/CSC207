package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * a handler that handles button undo, restart, hint, greedy hint and random hint
 */
public class UserButtonHandler implements EventHandler<ActionEvent> {
	//this will be the controller for hint, undo, restart
	
	private Othello othello;
	private Move move;
	
	/**
	 * construct a handler that handles button undo, restart, hint, greedy hint and random hint
	 * 
	 * @param othello
	 */
	public UserButtonHandler(Othello othello) {
		this.othello = othello;
	}

	/**
	 * handles button undo, restart, hint, greedy hint and random hint
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {

		Button source = (Button)event.getSource();
		if(source.getText() == "Undo") {
			this.othello.undo();
		}
		if (source.getText() == "Restart") {
			this.othello.restart();
		}
		if(source.getText() == "HINT!") {
			this.othello.getBestMove();
		}
		if(source.getText() == "GreedyHint") {
			this.othello.setGreedyHint();
		}
		if(source.getText() == "RandomHint") {
			this.othello.setRandomHint();
		}
		this.othello.updateMove();
	}

}
