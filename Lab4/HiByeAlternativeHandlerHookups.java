package guifx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HiByeAlternativeHandlerHookups extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// CONTROLS
		Button bHi, bBye; // Two reference to Button, no Button exist yet!
		bHi = new Button("hi");
		bBye = new Button("bye");
		TextField tf = new TextField(); // All at once
		
		// EVENT HANDLER CREATION
		// https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm#CEGJAAFD
		// Create button press event handlers
		HiByeEventHandler hbh1 = new HiByeEventHandler();
		HiByeEventHandler hbh2 = new HiByeEventHandler();			
		// EVENT HANDLER HOOKUP
		// Tell the buttons who they should call when they are pressed
		
		// https://docs.oracle.com/javase/8/javafx/api/javafx/event/Event.html
		
		// Alternative 0
		bHi.addEventHandler(ActionEvent.ACTION , hbh1);
		bBye.addEventHandler(ActionEvent.ACTION , hbh1);
		
		// Alternative 1
		// HiByeEventHandler hbh1 = new HiByeEventHandler();
		// bHi.setOnAction(hbh1);
		
		// Alternative 2 (inner class)
		/**
		 EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
           public void handle(ActionEvent e) {
        
           }
         };
         bhi.setOnAction(eventHandler);
		 */
		
		// Alternative 3 (anonymous inner class)
		/** 
		 bHi.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              
          }
         });
		 **/

		// Alternative 4 (Lambda)
		// https://docs.oracle.com/javase/8/javafx/api/javafx/event/EventHandler.html
		// https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html?is-external=true
		// https://blog.takipi.com/compiling-lambda-expressions-scala-vs-java-8/
		/**
		 bHi.setOnAction((event) -> {
	       
		 });
		 **/
		
		// LAYOUT
		HBox root = new HBox();
		// VBox root = new VBox();
		root.setPadding(new Insets(5));
		root.getChildren().addAll(bHi,bBye,tf);
		
		// SCENE
		Scene scene = new Scene(root); 
		// Scene scene = new Scene(root, 200, 200); 

		// STAGE
		stage.setTitle("Hi Bye");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
