package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.Observable;

/**
 * for bot player including random and greedy, set up the player for othello
 * and get move from bot player
 */
public class AIPlayer extends Observable{
	private String ai;
	private PlayerRandom pr;
	private PlayerGreedy pg;
	private Othello othello;
	private Move move;
	
	/**
	 * Constructor that set up the player for the game
	 * @param othello
	 */
	public AIPlayer(Othello othello) {
		this.othello = othello;
		String p1 = othello.getPlayer1();
		String p2 = othello.getPlayer2();
		if(p1 != "Human" || p2 != "Human") {
			if(p1 != "Human") {
				ai = p1;
			} else if(p2 != "Human"){
				ai = p2;
			}
		}
	}
	
	/**
	 * set ai to be a specific player typy: Random or greedy or human
	 * @param s
	 */
	public void setPlayer(String s) {
		this.ai = s;
	}
	
	/**
	 * set othello to be the othello that want to get a move from
	 * @param o
	 */
	public void setOthello(Othello o) {
		this.othello = o;
	}
	
	/**
	 * get move from player random or player greedy base on ai
	 */
	public void setMove() {
		if(ai == "Greedy") {
			pg = new PlayerGreedy(this.othello, othello.getWhosTurn());
			move = pg.getMove();
		} else if(ai == "Random") {
			pr = new PlayerRandom(this.othello, othello.getWhosTurn());
			move = pr.getMove();
		}
	}
	/**
	 * 
	 * @return ai move
	 */
	public Move getAIMove() {
		return move;
	}
}
