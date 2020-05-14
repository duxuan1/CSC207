package guifx;

import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

public class HiByeFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		initUI(stage);
	}

	private void initUI(Stage stage) {

		HBox pane = new HBox(5);
		Scene scene = new Scene(pane);

		pane.setPadding(new Insets(10));

		Button hi_btn = new Button("Hi");
		Button bye_btn = new Button("Bye");

		pane.getChildren().add(hi_btn);
		pane.getChildren().add(bye_btn);

		TextField txt = new TextField();
		txt.setPrefColumnCount(20);
		pane.getChildren().add(txt);

		// Hook up the event handler
		hi_btn.setOnAction(new HiByeEventHandler(txt));
		bye_btn.setOnAction(new HiByeEventHandler(txt));

		stage.setTitle("Hi Bye JavaFX");
		stage.setScene(scene);
		stage.show();	
	}
}
