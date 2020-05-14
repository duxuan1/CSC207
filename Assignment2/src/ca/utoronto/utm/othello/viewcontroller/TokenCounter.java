package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.*;

import javafx.scene.control.TextField;

/**
 * a textfield displaying current token numbers on the board of player1 nad player2
 * observer pattern is used that every move on gameboard will update this textfield
 */
public class TokenCounter extends TextField implements Observer {

	public char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	/**
	 * construct a textfield displaying current token numbers on the board of player1 nad player2
	 */
	public TokenCounter(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText("P1: " + String.valueOf(othello.getCount(P1)) + (" P2: " + String.valueOf(othello.getCount(P2))));
	}

	/**
	 * update displaying current token numbers on the board of player1 nad player2
	 */
	@Override
	public void update(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText("P1: " + String.valueOf(othello.getCount(P1)) + (" P2: " + String.valueOf(othello.getCount(P2))));
	}

}
