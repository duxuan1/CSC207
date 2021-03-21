package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.TextField;

/**
 * a textfield displaying the player1 nad player2's status, whether human, random or greedy
 * observer pattern is used that every move on gameboard will update this textfield
 */
public class PlayerDisplay extends TextField implements Observer {

	/**
	 * construct a textfield displaying the player1 nad player2's status, whether human, random or greedy
	 */
	public PlayerDisplay(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText(othello.getPlayer1() + " VS " + othello.getPlayer2());
	}

	/**
	 * update the  display that the player1 nad player2's status, whether human, random or greedy
	 */
	@Override
	public void update(Observable o) {
		this.setPrefSize(200, 100);
		this.setEditable(false);
		Othello othello = (Othello) o;
		this.setText(othello.getPlayer1() + " VS " + othello.getPlayer2());
	}

}
