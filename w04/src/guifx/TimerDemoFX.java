package guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Why not just use java.util.Timer and java.util.TimerTask with your GUI?
 * Answer: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm 
 * 
 * More about the Timeline class:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
 */

public class TimerDemoFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		initUI(stage);
	}

	void initUI(Stage stage) {

		VBox pane = new VBox(5);

		pane.setPadding(new Insets(10));

		Label lbl_tick = new Label("TICK");
		Label lbl_tock = new Label("TOCK");

		lbl_tick.setFont(new Font("Arial", 30));
		lbl_tock.setFont(new Font("Arial", 30));

		pane.getChildren().add(lbl_tick);
		pane.getChildren().add(lbl_tock);

		Timeline timer1 = new Timeline(new KeyFrame(Duration.millis(1500), 
				new TimerHandler("Tick", lbl_tick)));

		Timeline timer2 = new Timeline(new KeyFrame(Duration.millis(900), 
				new TimerHandler("Tock", lbl_tock)));

		timer1.setCycleCount(Animation.INDEFINITE);
		timer1.play();

		timer2.setCycleCount(Animation.INDEFINITE);
		timer2.play();

		Scene scene = new Scene(pane, 500, 300);
		stage.setTitle("TimerDemo JavaFX");
		stage.setScene(scene);
		stage.show();
	}
}
