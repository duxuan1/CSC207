package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * opponentHandler is a handler that handles player chooser and setting player 
 */
public class OpponentHandler implements EventHandler<ActionEvent> {

	private Othello othello;

	/**
	 * Construct a handler that handles player choosen and setting player 
	 * 
	 * @param othello
	 */
	public OpponentHandler(Othello othello) {
		this.othello = othello;
	}

	/**
	 * update opponentHandler by setting player 
	 * 
	 * @param
	 */
	@Override
	public void handle(ActionEvent event) {
		OpponentFactory factory = new OpponentFactory(othello);
		factory.handle(event);
	}
}
