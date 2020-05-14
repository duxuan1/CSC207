package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * TimerHandler that handles all timer related observers
 * observer pattern is used that every move on gameboard will update this button
 */
public class TimerHandler implements EventHandler<ActionEvent>{
	private TextField p1min;
	private TextField p1sec;
	private TextField p2min;
	private TextField p2sec;
	private int minutes1 = 0;
	private int minutes2 = 0;
	private int seconds1 = 0;
	private int seconds2 = 0;
	private Othello othello;
	private Timer timer;
	private WhosWon whoswon;
	
	/**
	 * construct a TimerHandler that handles all timer related observers
	 * 
	 * @param txt1
	 * @param txt2
	 * @param txt3
	 * @param txt4
	 * @param timer
	 * @param othello
	 * @param whoswon
	 */
	public TimerHandler(TextField txt1, TextField txt2, TextField txt3, TextField txt4, Timer timer, Othello othello, WhosWon whoswon) {
		this.p1min = txt1;
		this.p1sec = txt2;
		this.p2min = txt3;
		this.p2sec = txt4;
		this.timer = timer;
		this.othello = othello;
		this.whoswon = whoswon;
	}
	
	/**
	 * construct a TimerHandler that handles all timer related observers
	 * 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		minutes1 = Integer.parseInt(this.p1min.getText());
		seconds1 = Integer.parseInt(this.p1sec.getText());
		minutes2 = Integer.parseInt(this.p2min.getText());
		seconds2 = Integer.parseInt(this.p2sec.getText());
		Button source = (Button) event.getSource();
		if(source.getText()=="Start Timer") {
			timer.start(othello,minutes1,seconds1,minutes2,seconds2,whoswon);
			p1min.setEditable(false);
			p1sec.setEditable(false);
			p2min.setEditable(false);
			p1sec.setEditable(false);
		}else if(source.getText()=="Reset Timer") {
			p1min.setEditable(true);
			p1sec.setEditable(true);
			p2min.setEditable(true);
			p1sec.setEditable(true);
			timer.reset();
		}
	}
}