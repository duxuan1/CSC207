package guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

public class LayoutComplex extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		initUI(stage);
	}

	private void initUI(Stage stage) {

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		
		pane.setLeft(new Button("WEST"));
		pane.setRight(new Button("EAST"));
		pane.setTop(new Button("NORTH"));
		pane.setBottom(new Button("SOUTH"));
		
		GridPane cpane = new GridPane();
		for (int i = 0; i < 9; i++) {
			cpane.add(new Button("Centre " + i), i % 3, i / 3);
		}
		
		// put the grid pane at the centre of the border pane
		pane.setCenter(cpane);

		Scene scene = new Scene(pane);
		stage.setTitle("Complex Layout JavaFX");
		stage.setScene(scene);
		stage.show();

	}
}
