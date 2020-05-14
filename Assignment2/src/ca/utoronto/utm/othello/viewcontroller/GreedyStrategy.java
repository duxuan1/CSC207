package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Strategy pattern is used for opponent chooser
 * a greedy strategy that will set a greedy player in the game and 
 * automatically generating greedy moves after every human move
 */
public class GreedyStrategy implements EventHandler<ActionEvent>{
	private Othello othello;
	private AIPlayer ai;
	
	/**
	 * construct a greedy strategy that will set a greedy player in the game and 
	 * automatically generating greedy moves after every human move
	 * 
	 * @param othello
	 */
	public GreedyStrategy(Othello othello) {
		this.othello = othello;
		this.ai = new AIPlayer(othello);
	}
	
	/**
	 * player greedy will make a greedy move on the board and will generate a 
	 * greedy move after every human move
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		othello.setAI(ai);
		int row = -1;
		int col = -1;

		this.othello.setPlayer("Greedy");

		
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
