package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *gameBoardHandler handles all tokens' moving on the board, renew the 
 * board and providing hints
 * 
 */
public class GameBoardHandler implements EventHandler<ActionEvent> {

	private Othello othello;
	private AIPlayer ai;
	
	/**
	 * construct a gameBoardHandler that all token moves on the board, renew the 
	 * board and providing hints
	 * 
	 * @param othello
	 */
	public GameBoardHandler(Othello othello) {
		this.othello = othello;
	}
	
	/**
	 * make move base on button pressed coordinates
	 * or if is playing with ai, let ai make a move after human moves
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		int row = GridPane.getRowIndex(source);
		int col = GridPane.getColumnIndex(source);
		String p1 = othello.getPlayer1();
		String p2 = othello.getPlayer2();

		if (0 <= row && row <= 7 && col >= 0 && col <= 7) {
			this.othello.move(row, col);
			Othello copy = othello.copy();
			this.othello.addHistory(copy);
		}
		this.othello.updateMove();
		
		
		if(othello.check()) {
			Move move = othello.getAIMove();
			row = move.getRow();
			col = move.getCol(); 
				
			if (0 <= row && row <= 7 && col >= 0 && col <= 7) {
				this.othello.move(row, col);
				Othello copy = othello.copy();
				this.othello.addHistory(copy);
			}
			this.othello.updateMove();
		}
		
	}
}