package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

import ca.utoronto.utm.mvcexample.CButtonPressEventHandler;
import ca.utoronto.utm.mvcexample.MCounter;
import ca.utoronto.utm.mvcexample.VCount;
import ca.utoronto.utm.mvcexample.VParity;
import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Othello application that display the gui for othello game
 */
public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	public char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	
	/**
	 * display the gui for othello game
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller

		// MODEL
		Othello othello = new Othello();
		ArrayList<Othello> history = new ArrayList<Othello>();

		// CONTROLLER
		// CONTROLLER->MODEL hookup

		GameBoardHandler myHandler = new GameBoardHandler(othello);
		UserButtonHandler hintContro = new UserButtonHandler(othello); //this is the controller for undo, restart, hint 
		OpponentHandler aiContro = new OpponentHandler(othello); // this is the controller for opponent players

		// VIEW COMPONENTS
		TokenCounter tokenCount = new TokenCounter(othello);
		WhosNext whosNext = new WhosNext(othello);
		WhosWon whosWon = new WhosWon(othello);
		PlayerDisplay playerDisplay = new PlayerDisplay(othello);
		Timer timer = new Timer(othello);
		HintDisplay hintDisplay = new HintDisplay(othello);
		GridPane gameBoard = new GridPane();
		Label p1time = new Label("Set time limit for P1: (min/sec)");
		Label p2time = new Label("Set time limit for P2: (min/sec)");
		P1mins p1min = new P1mins();
		P1secs p1sec = new P1secs();
		P2mins p2min = new P2mins();
		P2secs p2sec = new P2secs();
		gameBoard.setStyle("-fx-background-color:POWDERBLUE");
		gameBoard.setPrefSize(1600, 900);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				TokenButton b = new TokenButton(othello, row, col);
				b.setOnAction(myHandler);
				gameBoard.add(b, col, row);
				othello.attach(b);
			}	
		}

		Button vshuman = new Button("Choose PlayerHuman as your opponent");
		vshuman.setPrefSize(350, 50);
		Button vsrandom = new Button("Choose PlayerRandom as your opponent");
		vsrandom.setPrefSize(350, 50);
		Button vsgreedy = new Button("Choose PlayerGreedy as your opponent");
		vsgreedy.setPrefSize(350, 50);
		Button undo = new Button("Undo");
		undo.setPrefSize(100, 50);
		Button restart = new Button("Restart");
		restart.setPrefSize(100, 50);
		Button hint = new Button("HINT!");
		hint.setPrefSize(100, 50);
		Button greedyHint = new Button("GreedyHint");
		greedyHint.setPrefSize(100, 50);
		Button randomHint = new Button("RandomHint");
		randomHint.setPrefSize(100, 50);
		Button starttimer = new Button("Start Timer");
		starttimer.setPrefSize(100,50);
		Button resettimer = new Button("Reset Timer");
		resettimer.setPrefSize(100,50);

		// VIEW LAYOUT
		gameBoard.add(tokenCount, 9, 2);
		gameBoard.add(whosNext, 9, 1);
		gameBoard.add(whosWon, 9, 0);
		gameBoard.add(playerDisplay, 9, 3);
		gameBoard.add(timer, 9, 5);
		gameBoard.add(hintDisplay, 9, 4);
		gameBoard.add(p1time, 9, 6);
		gameBoard.add(p2time, 9, 7);
		gameBoard.add(p1min, 10, 6);
		gameBoard.add(p1sec, 11, 6);
		gameBoard.add(p2min, 10, 7);
		gameBoard.add(p2sec, 11, 7);
		gameBoard.add(starttimer, 100, 6);
		gameBoard.add(resettimer, 100, 7);
		gameBoard.add(vshuman, 100, 0);
		gameBoard.add(vsrandom, 100, 1);
		gameBoard.add(vsgreedy, 100, 2);
		gameBoard.add(undo, 100, 3);
		gameBoard.add(restart, 100, 4);
		gameBoard.add(hint, 110, 5);
		gameBoard.add(greedyHint, 110, 6); 
		gameBoard.add(randomHint, 110, 7);

		// VIEW SCENE
		Scene scene = new Scene(gameBoard);
		// Scene scene = new Scene(root, 200, 200);

		// VIEW STAGE
		stage.setTitle("Othello");
		stage.setScene(scene);

		// VIEW->CONTROLLER hookup
		// Note, for code below, could have had two different
		// controllers, one for each button.
		vshuman.setOnAction(aiContro);
		vsrandom.setOnAction(aiContro);
		vsgreedy.setOnAction(aiContro);
		
		undo.setOnAction(hintContro);
		restart.setOnAction(hintContro);
		hint.setOnAction(hintContro);
		greedyHint.setOnAction(hintContro);
		randomHint.setOnAction(hintContro);
		starttimer.setOnAction(new TimerHandler(p1min,p1sec,p2min,p2sec,timer,othello, whosWon));
		resettimer.setOnAction(new TimerHandler(p1min,p1sec,p2min,p2sec,timer,othello, whosWon));

		// MODEL->VIEW hookup
		othello.attach(tokenCount);
		othello.attach(whosNext);
		othello.attach(whosWon);
		othello.attach(playerDisplay);
		othello.attach(hintDisplay);
		othello.attach(timer);
		

		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
