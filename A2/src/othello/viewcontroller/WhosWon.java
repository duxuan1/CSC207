package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.*;

import javafx.scene.control.TextField;

/**
 * a textfield displaying whether player1 or player 2 won for othello game
 * observer pattern is used that every move on gameboard will update this textfield
 */
public class WhosWon extends TextField implements Observer {

	public char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';

	/**
	 * construct a textfield displaying whether player1 or player 2 to play for othello game
	 * 
	 * @param o
	 */
	public WhosWon(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText(String.valueOf("Game is in Progress"));
	}

	/**
	 * update whether player1 or player 2 to play for othello game
	 * 
	 * @param o
	 */
	@Override
	public void update(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		if (othello.getWinner() == EMPTY) {
			if(!othello.isGameOver()) {
				this.setText(String.valueOf("Game is in Progress"));
			}else {
				this.setText("GAME OVER,TIME OUT");
			}
		} else if (othello.getWinner() == BOTH) {
			this.setText(String.valueOf("Tie"));
		} else {
			this.setText(String.valueOf(othello.getWinner()) + " won");
		}
	}

}