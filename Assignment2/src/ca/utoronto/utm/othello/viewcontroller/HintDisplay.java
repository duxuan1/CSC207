package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.TextField;

/**
 * a textfield displaying the hint for othello game
 * observer pattern is used that every move on gameboard will update this textfield
 */
public class HintDisplay extends TextField implements Observer{
	
	/**
	 * construct a textfield displaying the hint for othello game
	 * 
	 * @param 
	 */
	public HintDisplay(Observable o) {
		this.setPrefSize(200, 100);
		Othello othello = (Othello)o;
		
		this.setText("( "+othello.getMove().getRow() + ", " + othello.getMove().getCol()+ " )");	
	}

	/**
	 * update displaying the hint for othello game
	 * 
	 * @param 
	 */
	@Override
	public void update(Observable o) {
		this.setPrefSize(200, 100);
		Othello othello = (Othello)o;
		
		this.setText("( "+othello.getMove().getRow() + ", " + othello.getMove().getCol()+ " )");
	}

}