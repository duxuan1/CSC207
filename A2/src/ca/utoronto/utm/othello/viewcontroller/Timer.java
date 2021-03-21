package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer extends Label implements Observer {
	private int minutesleft1 = 0;
	private int secondsleft1 = 0;
	private int minutesleft2 = 0;
	private int secondsleft2 = 0;
	private Timeline timeline;
	private int minutesleft;
	private int secondsleft;
	private WhosWon whoswon;
	/**
	 * Construct a timer that displaying on the gameboard.
	 * @param o
	 */
	public Timer (Observable o) {
		Othello othello = (Othello)o;
		setText("Time left for " + othello.getWhosTurn() + " : " + minutesleft1  + " : " + secondsleft1 );
	}
	/**
	 * Update the timer depends on who's turn.
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello)o;
		save(othello);
		switchtimer(othello);
	}
	
	/**
	 * Get time limit from user and start the timer, it will finish the game if time out.
	 * @param othello
	 * @param mins1
	 * @param secs1
	 * @param mins2
	 * @param secs2
	 * @param whoswon
	 */
	public void start(Othello othello, int mins1, int secs1, int mins2, int secs2, WhosWon whoswon) {
		timeline = new Timeline();
		minutesleft1 = mins1;
		secondsleft1 = secs1;
		minutesleft2 = mins2;
		secondsleft2 = secs2;
		switchtimer(othello);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				if(secondsleft==0) {
					minutesleft -= 1;
					secondsleft = 60;
				}
				secondsleft -=1;
				setText("Time left for " + othello.getWhosTurn() + " : " + minutesleft  + " : " + secondsleft );
				if(secondsleft==0&&minutesleft==0) {
					if(othello.getWhosTurn()=='X') {
						whoswon.setText("O won");
					}else {
						whoswon.setText("X won");
					}
					othello.setwhosturn(' ');
					timeline.stop();
				}
			}
		}));
		timeline.play();;
	}
	/**
	 * Let the timer show time left depends on Who's turn.
	 * @param othello
	 */
	public void switchtimer(Othello othello) {
		if(othello.getWhosTurn()=='X') {
			minutesleft = minutesleft1;
			secondsleft = secondsleft1;
		}else {
			minutesleft = minutesleft2;
			secondsleft = secondsleft2;
		}
	}
	/**
	 * Pause player's time if it is not his turn.
	 * @param othello
	 */
	public void save(Othello othello) {
		if(othello.getWhosTurn()=='X') {
			minutesleft2 = minutesleft;
			secondsleft2 = secondsleft;
		}else {
			minutesleft1 = minutesleft;
			secondsleft1 = secondsleft;
		}
	}
	/**
	 * reset the timer
	 */
	public void reset() {
		minutesleft1 = 0;
		secondsleft1 = 0;
		minutesleft2 = 0;
		secondsleft2 = 0;
		timeline.stop();
	}
}
