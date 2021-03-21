package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Used strategy pattern for opponent chooser
 * a human strategy that will set a human player in the game 
 */
public class HumanStrategy implements EventHandler<ActionEvent> {
	private Othello othello;
	private AIPlayer ai;

	/**
	 * construct a human strategy that will set a random player in the game
	 * 
	 * @param othello
	 */
	public HumanStrategy(Othello othello) {
		this.othello = othello;
		this.ai = new AIPlayer(othello);
	}

	/**
	 * player human will make a human move on the board
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		othello.setAI(ai);
		int row = -1;
		int col = -1;

		this.othello.setPlayer("Human");

		
		if(othello.check()) {
			Move move = othello.getAIMove();
			row = move.getRow();
			col = move.getCol(); 
		}
	
		
		if (0 <= row && row <= 7 && col >= 0 && col <= 7) {
			this.othello.move(row, col);
			Othello copy = othello.copy();
			this.othello.addHistory(copy);
		}

		this.othello.updateMove();
	}

}