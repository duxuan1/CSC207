package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

/**
 * a button that displaying on the gameboard, showing token of whether player1
 * or player2 or no player on this position with appropriate color, a
 * transparent circle will be available move for current player and yellow/red
 * circle for greedy/random hit on the board 
 * observer pattern is used that every move on gameboard will update this button
 */
public class TokenButton extends Button implements Observer {

	public char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B', CANMOVE = 'Y', GREEDY = 'G', RANDOM = 'R';
	private int row;
	private int col;
	private char status;
	private AIPlayer ai;

	/**
	 * Construct a button that displaying on the gameboard, showing token of whether player1
	 * or player2 or no player on this position with appropriate color, a
	 * transparent circle will be available move for current player and yellow/red
	 * circle for greedy/random hit on the board 
	 * 
	 * @param o
	 * @param row
	 * @param col 
	 */
	public TokenButton(Observable o, int row, int col) {
		this.setPrefSize(100, 100);
		// Initial position
		this.row = row;
		this.col = col;
		this.status = EMPTY;

		// Model
		Othello othello = (Othello) o;
		Othello copy = othello.copy();
		copy.checkMove();

		// Create button image
		Circle circle = new Circle(20);

		if (copy.getToken(row, col) == P1) {
			this.status = P1;
			circle.setFill(Color.BLACK);
			this.setGraphic(circle);
		} else if (copy.getToken(row, col) == P2) {
			this.status = P2;
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.WHITE);
			this.setGraphic(circle);
		} else if (copy.getToken(row, col) == CANMOVE) {
			this.status = CANMOVE;
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.TRANSPARENT);
			this.setGraphic(circle);
		} else {
			this.status = EMPTY;
			this.setGraphic(null);
		}
	}

	/**
	 *update the display on the gameboard, showing token of whether player1
	 * or player2 or no player on this position with appropriate color, a
	 * transparent circle will be available move for current player and yellow/red
	 * circle for greedy/random hit on the board 
	 * 
	 * @param o
	 */
	@Override
	public void update(Observable o) {

		// Model
		Othello othello = (Othello) o;
		Othello copy = othello.copy();
		copy.checkMove();

		if (othello.getGreedyHint()) {
			copy.getGreedyMove();
		}
		if (othello.getRandomHint()) {
			copy.getRandomMove();
		}

		// Create button image
		Circle circle = new Circle(20);
		if (copy.getToken(row, col) != this.status) {
			if (copy.getToken(row, col) == P1) {
				this.status = P1;
				circle.setFill(Color.BLACK);
				this.setGraphic(circle);

				// animation
				FillTransition fillTransition = new FillTransition(Duration.seconds(0.75), circle);
				fillTransition.setFromValue(Color.WHITE);
				fillTransition.setToValue(Color.BLACK);
				fillTransition.setCycleCount(1);
				fillTransition.setAutoReverse(true);
				fillTransition.play();
			} else if (copy.getToken(row, col) == P2) {
				this.status = P2;
				circle.setStroke(Color.BLACK);
				circle.setFill(Color.WHITE);
				this.setGraphic(circle);

				// animation
				FillTransition fillTransition = new FillTransition(Duration.seconds(0.75), circle);
				fillTransition.setFromValue(Color.BLACK);
				fillTransition.setToValue(Color.WHITE);
				fillTransition.setCycleCount(1);
				fillTransition.setAutoReverse(true);
				fillTransition.play();
			} else if (copy.getToken(row, col) == CANMOVE) {
				this.status = CANMOVE;
				circle.setStroke(Color.BLACK);
				circle.setFill(Color.TRANSPARENT);
				this.setGraphic(circle);
			} else if (copy.getToken(row, col) == GREEDY) {
				this.status = GREEDY;
				circle.setStroke(Color.BLACK);
				circle.setFill(Color.YELLOW);
				this.setGraphic(circle);
			} else if (copy.getToken(row, col) == RANDOM) {
				this.status = RANDOM;
				circle.setStroke(Color.BLACK);
				circle.setFill(Color.RED);
				this.setGraphic(circle);
			} else {
				this.status = EMPTY;
				this.setGraphic(null);
			}
		}
	}
}
