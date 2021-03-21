package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.*;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * a textfield displaying whether player1 or player 2 to play for othello game
 * observer pattern is used that every move on gameboard will update this textfield
 */
public class WhosNext extends TextField implements Observer {

	public char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';

	/**
	 * construct a textfield to show whether player1 or player 2 to play for othello game
	 * 
	 * @param o
	 */
	public WhosNext(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText(String.valueOf(othello.getWhosTurn()) + " is next");
	}

	/**
	 * update the display that whether player1 or player 2 to play for othello game
	 * 
	 * @param o
	 */
	@Override
	public void update(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText(String.valueOf(othello.getWhosTurn()) + " is next");
	}

}