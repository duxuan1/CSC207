package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.viewcontroller.AIPlayer;
import ca.utoronto.utm.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello extends Observable {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private OthelloBoard board = new OthelloBoard(Othello.DIMENSION);
	private char whosTurn = OthelloBoard.P1;
	private int numMoves = 0;
	private String player1 = "Human";
	private String player2 = "Human";
	private ArrayList<Othello> history = new ArrayList<Othello>();
	private Move move = new Move(-1, -1);
	private AIPlayer ai;
	private boolean greedyHint = false;
	private boolean randomHint = false;

	/**
	 * check if a ai player is in the game
	 * 
	 * @return true if a ai hint could be given, false otherwise
	 */
	public boolean check() {
		if (player1 != "Human" || player2 != "Human") {
			if (player1 != "Human") {
				if (this.whosTurn != 'O') {
					return true;
				}
			} else if (player2 != "Human") {
				if (this.whosTurn != 'X') {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * set a ai player
	 */
	public void setAI(AIPlayer ai) {
		this.ai = ai;
	}

	/**
	 * get ai player for othello
	 * 
	 * @return ai player
	 */
	public AIPlayer getAI() {
		return this.ai;
	}

	/**
	 * set player1 and player2 for othello game
	 */
	public void setPlayer(String s) {
		this.player1 = "Human";
		this.player2 = "Human";
		if (this.getWhosTurn() == OthelloBoard.P1) {
			this.player1 = s;
		} else {
			this.player2 = s;
		}
		ai.setPlayer(s);
	}

	/**
	 * get a move from ai player
	 * 
	 * @return a move that made by ai player
	 */
	public Move getAIMove() {
		ai.setMove();
		return ai.getAIMove();
	}

	/**
	 * get player1
	 * 
	 * @return player1
	 */
	public String getPlayer1() {
		return this.player1;
	}

	/**
	 * player2
	 * 
	 * @return player2
	 */
	public String getPlayer2() {
		return this.player2;
	}

	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return the token at position row, col.
	 */
	public char getToken(int row, int col) {
		return this.board.get(row, col);
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		if (this.board.move(row, col, this.whosTurn)) {
			this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn);
			char allowedMove = board.hasMove();
			if (allowedMove != OthelloBoard.BOTH)
				this.whosTurn = allowedMove;
			this.numMoves++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		return board.getCount(player);
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if (!this.isGameOver())
			return OthelloBoard.EMPTY;
		if (this.getCount(OthelloBoard.P1) > this.getCount(OthelloBoard.P2))
			return OthelloBoard.P1;
		if (this.getCount(OthelloBoard.P1) < this.getCount(OthelloBoard.P2))
			return OthelloBoard.P2;
		return OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return this.whosTurn == OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return a copy of this. The copy can be manipulated without impacting current board.
	 */
	public Othello copy() {
		Othello o = new Othello();
		o.board = this.board.copy();
		o.numMoves = this.numMoves;
		o.whosTurn = this.whosTurn;
		return o;
	}

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return board.toString() + "\n";
	}

	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while (!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if (o.move(row, col)) {
				System.out.println("makes move (" + row + "," + col + ")");
				System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
			}
		}

	}

	/**
	 * restart the game
	 */
	public void restart() {
		this.numMoves = 0;
		this.board = new OthelloBoard(Othello.DIMENSION);
		this.whosTurn = OthelloBoard.P1;
		this.player1 = "Human";
		this.player2 = "Human";
		this.history = new ArrayList<Othello>();
		this.greedyHint = false;
		this.randomHint = false;
	}

	/**
	 * notify observers
	 */
	public void updateMove() {
		this.notifyObservers();
	}

	/**
	 * check all available moves and make all available moves on the board by letter
	 * Y
	 */
	public void checkMove() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				for (int drow = -1; drow <= 1; drow++) {
					for (int dcol = -1; dcol <= 1; dcol++) {
						if (this.board.hasMove(row, col, drow, dcol) == this.whosTurn) {
							this.board.setNotation(row, col);
						}
					}
				}
			}
		}
	}

	/**
	 * get the current move
	 * 
	 * @return return current move
	 */
	public Move getMove() {
		return this.move;
	}

	/**
	 * set greedy hint to be true and random hit false
	 */
	public void setGreedyHint() {
		if(this.greedyHint == false) {
			this.greedyHint = true;
			this.randomHint = false;
		} else {
			this.greedyHint = false;
		}
		
	}

	/**
	 * get greedy hint
	 * 
	 * @return return greedyHint
	 */
	public boolean getGreedyHint() {
		return this.greedyHint;

	}

	/**
	 * get a greedy move and setting greedy hit to be true
	 */
	public void getGreedyMove() {
		int r = -1;
		int c = -1;
		int bestMoveCount = 0;
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				if (this.getToken(row, col) == 'Y') {
					if (this.getCount(this.whosTurn) > bestMoveCount) {
						bestMoveCount = this.getCount(this.whosTurn);
						r = row;
						c = col;
					}
				}
			}
		}
		if (0 <= r && r <= 7 && c >= 0 && c <= 7) {
			this.board.setGreedyHint(r, c);
		}
	}

	/**
	 * set random hint
	 * 
	 * @return return RandomHint
	 */
	public void setRandomHint() {
		if(this.randomHint == false) {
			this.randomHint = true;
			this.greedyHint = false;
		} else {
			this.randomHint = false;
		}
		
	}

	/**
	 * get random hint
	 */
	public boolean getRandomHint() {
		return this.randomHint;

	}

	/**
	 * get random move and set random hint
	 */
	public void getRandomMove() {
		Random rand = new Random();
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				if (this.getToken(row, col) == 'Y') {
					moves.add(new Move(row, col));
				}
			}
		}
		Move m = moves.get(rand.nextInt(moves.size()));
		int r = m.getRow();
		int c = m.getCol();
		if (0 <= r && r <= 7 && c >= 0 && c <= 7) {
			this.board.setRandomHint(r, c);
		}
	}

	/**
	 * get best move
	 */
	public void getBestMove() {
		Othello othelloCopy = this.copy();
		Move bestMove = new Move(0, 0);
		int bestMoveCount = othelloCopy.getCount(this.whosTurn);
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				othelloCopy = this.copy();
				if (othelloCopy.move(row, col) && othelloCopy.getCount(this.whosTurn) > bestMoveCount) {
					bestMoveCount = othelloCopy.getCount(this.whosTurn);
					bestMove = new Move(row, col);
				}
			}
		}
		// Better Strategy
		if (othelloCopy.move(0, 0)) {
			bestMove = new Move(0, 0);
		} else if (othelloCopy.move(0, 7)) {
			bestMove = new Move(0, 7);
		} else if (othelloCopy.move(7, 0)) {
			bestMove = new Move(7, 0);
		} else if (othelloCopy.move(7, 7)) {
			bestMove = new Move(7, 7);
		}
		this.move = bestMove;
	}

	/**
	 * add old version of othello to history
	 */
	public void addHistory(Othello o) {
		this.history.add(o);
	}

	/**
	 * get history
	 * 
	 * @return return history
	 */
	public ArrayList<Othello> getHistory() {
		return this.history;
	}

	/**
	 * othello game get back to previous version
	 */
	public void undo() {
		if (!this.history.isEmpty()) {
			this.history.remove(this.history.size() - 1);
			if (!this.history.isEmpty()) {
				Othello prev = this.history.remove(this.history.size() - 1);
				this.numMoves = prev.numMoves;
				this.board = prev.board;
				this.whosTurn = prev.whosTurn;
				this.player1 = prev.player1;
				this.player2 = prev.player2;
			} else {
				this.restart();
			}
		} else if (this.history.isEmpty()) {
			this.restart();
		}
	}

	/**
	 * set whosturn to a new whosturn
	 */
	public void setwhosturn(char whosturn) {
		this.whosTurn = whosturn;
	}
}
