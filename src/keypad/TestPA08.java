package keypad;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * File: PAssign08.java
 * Class: CSCI 1302
 * Author: Bailey Diehl
 * Created on: Nov 12, 2024
 * Last Modified: Nov 13, 2024
 * Description:  "Prints Out" a Smart Lock
 * GitHub Link: https://github.com/DarthDiehl/hello-world
 */
public class TestPA08 extends Application {
	private PAssign08 keyPane;
    private GridPane mainPane;
    HBox radioButtonPane = new HBox(10);

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		mainPane = new GridPane();

		// a wrapper class to keep stuff off of each other
		FlowPane dressPane = new FlowPane();

		// create a KeyPadPane
		//PAssign08 keyPane = new PAssign08(); // default keypad layout
		keyPane = new PAssign08(true); // custom keypad layout
		keyPane.setAlignment(Pos.CENTER);
		keyPane.setPadding(new Insets(5));

		// Making radio buttons and wrapping it together function & display
		RadioButton rbDefault = new RadioButton("Defualt");
		RadioButton rbCustom = new RadioButton("Custom");
		ToggleGroup group = new ToggleGroup();
		rbDefault.setToggleGroup(group);
		rbCustom.setToggleGroup(group);
		radioButtonPane.getChildren().addAll(rbDefault,new Label("or"), rbCustom);
		radioButtonPane.setAlignment(Pos.CENTER);
		radioButtonPane.setPadding(new Insets(10));
		// Listeners
		rbDefault.setOnAction(e -> switchKeypadLayout(false));
		rbCustom.setOnAction(e -> switchKeypadLayout(true));

		// Building gridPane - main display
		// Didn't want repeating code so you'll have to choose your display.
							// node, x, y
		mainPane.add(new Label("Choose Layout:"), 0, 0);
		mainPane.add(radioButtonPane, 1, 0);
//		mainPane.add(keyPane.addStyle(), 1, 1);
//		mainPane.add(keyPane, 1, 2);
//		mainPane.add(keyPane.addImage(), 0, 2);
//		mainPane.add(keyPane.addLock(), 2, 2);
		dressPane.getChildren().add(mainPane);
		dressPane.setAlignment(Pos.CENTER);

		// Scene and Stage
		Scene scene = new Scene(dressPane, 300, 300);
		primaryStage.setTitle("Test PA08"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setMinHeight(250);
		primaryStage.setMinWidth(250);
		primaryStage.show(); // Display the stage
	}
	//
	private void switchKeypadLayout(boolean isCustom) {
        // Create the new keyPad with the desired layout
        keyPane = new PAssign08(isCustom);
        keyPane.setAlignment(Pos.CENTER);
        keyPane.setPadding(new Insets(5));
        
        // Replace the current keyPane in the GridPane without directly manipulating children
        mainPane.getChildren().clear();
        
        // Add radio button pane again at position (1, 0) in the GridPane
        mainPane.add(radioButtonPane, 1, 0); 
        
        // Add the input field, keypad, image, and lock again with the same constraints
        mainPane.add(keyPane.addStyle(), 1, 1); // Input field
        mainPane.add(keyPane, 1, 2); // KeyPadPane
        mainPane.add(keyPane.addImage(), 0, 2); // Image on the left
        mainPane.add(keyPane.addLock(), 2, 2); // Lock on the right
    }

	public static void main(String[] args) { 
		launch(args);
	}
}