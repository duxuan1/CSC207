package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Strategy pattern is used for opponent chooser
 * a random strategy that will set a random player in the game and 
 * automatically generating random moves after every human move
 */
public class RandomStrategy implements EventHandler<ActionEvent>{
	
	private Othello othello;
	private AIPlayer ai;

	/**
	 * construct a random strategy that will set a random player in the game and 
	 * automatically generating random moves after every human move
	 * 
	 * @param othello
	 */
	public RandomStrategy(Othello othello) {
		this.othello = othello;
		this.ai = new AIPlayer(othello);
	}

	/**
	 * player random will make a random move on the board and will generate a 
	 * random move after every human move
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		othello.setAI(ai);
		int row = -1;
		int col = -1;

		this.othello.setPlayer("Random");

		
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
