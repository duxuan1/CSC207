package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Factory pattern used 
 * opponent factory used to determine whether human, random or greedy strategy 
 * to be used 
 */
public class OpponentFactory implements EventHandler<ActionEvent>{
	

	private Othello othello;

	/**
	 * construct a opponent factory used to determine whether human, random or greedy strategy 
	 * to be used 
	 */
	public OpponentFactory(Othello othello) {
		this.othello = othello;
	}

	/**
	 * determine whether human, random or greedy strategy 
	 * to be used 
	 */
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		if (source.getText() == "Choose PlayerHuman as your opponent") {
			HumanStrategy strategy = new HumanStrategy(othello);
			strategy.handle(event);
		} else if (source.getText() == "Choose PlayerGreedy as your opponent") {
			GreedyStrategy strategy = new GreedyStrategy(othello);
			strategy.handle(event);
		} else if (source.getText() == "Choose PlayerRandom as your opponent") {
			RandomStrategy strategy = new RandomStrategy(othello);
			strategy.handle(event);
		}

	}

}
