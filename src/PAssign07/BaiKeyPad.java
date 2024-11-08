package PAssign07;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaiKeyPad extends KeyPadPane{
	protected Label lbluserNum;
	protected TextField tfuserInput;
	protected Button btnEnter = new Button("ENTER");
	protected Button btnClear = new Button("CLEAR");
	protected Button btnCancel = new Button("");
	protected Button btnBlank3 = new Button("");
	protected ImageView backSpace = new ImageView(new Image(getClass().getResource("/image/backspace.png").toExternalForm()));

	// Wrapper Classes for use in PAssign07
	protected VBox opBttns = new VBox(2);
	protected HBox disBttns = new HBox(5);
	protected GridPane atmDisplay = new GridPane();

	public BaiKeyPad() {
		// Extra parts Specific to this File.
		lbluserNum = new Label("Enter 4-Digit PIN:");
		tfuserInput = new TextField();
		tfuserInput.setPromptText("PIN");


		// Styling
		btnEnter.setStyle("-fx-background-color: green;");
		btnCancel.setStyle("-fx-background-color: yellow;");
		btnClear.setStyle("-fx-background-color: red;");
		btnCancel.setGraphic(backSpace);

		// Set button dimensions
		// Tried binding but was complicated
		double buttonWidth = 60;
		double buttonHeight = 20;

		btnEnter.setPrefSize(buttonWidth, buttonHeight);
		btnCancel.setPrefSize(buttonWidth, buttonHeight);
		btnClear.setPrefSize(buttonWidth, buttonHeight);
		btnBlank3.setPrefSize(buttonWidth, buttonHeight);

		// Resize and style the backspace image
		backSpace.setFitWidth(buttonWidth * 0.6); 
		backSpace.setFitHeight(buttonHeight * 0.6);

		// Customize the Prof.Williams event handlers to produce input into textField.
		registerEventHandlers();

		// OnAction Clear Button
		btnClear.setOnAction(e -> tfuserInput.clear());

		// OnAction BackSpace Button
		btnCancel.setOnAction(e -> {
			try {
				String userInputCopy = tfuserInput.getText();
				if (!userInputCopy.isEmpty()) {
					String userInputAdj = userInputCopy.substring(0, userInputCopy.length() - 1);
					tfuserInput.setText(userInputAdj);
				} else {
					tfuserInput.setText("No Input to Trim");
				}
			} catch (NumberFormatException ex) {
				tfuserInput.setText("Failed to Trim Error: Invalid Inputs");
			}
		});

		// OnAction Enter Button
		btnEnter.setOnAction(e -> {
			try {
				String userInputCopy2 = tfuserInput.getText();
				if(!userInputCopy2.isEmpty() && userInputCopy2.length() == 4) {
					tfuserInput.setText("Welcome - PIN: Accepted");
					
					// Produces a picture of "Courage".
					Stage stage1 = new Stage();
					Pane puppyPane = new Pane();
					ImageView puppy = new ImageView(new Image(getClass().getResource("/image/puppy.png").toExternalForm()));
					puppy.setFitWidth(200);
					puppy.setFitHeight(200);
					puppy.setPreserveRatio(true);
					puppyPane.getChildren().add(puppy);
					Scene scene1 = new Scene(puppyPane,150,200);
					stage1.setTitle("Puppy!!!");
					stage1.setScene(scene1);
					stage1.show();

				} else {
					tfuserInput.setText("Error - Only 4 Digits");					
				}
			} catch (NumberFormatException ex) {
				tfuserInput.setText("Invalid Input Error: Need Numbers Plz");
			}

		});

		// Adding the HBox to the VBox
		opBttns.getChildren().addAll(btnEnter, btnCancel, btnClear, btnBlank3);
		disBttns.getChildren().addAll(this, opBttns);
		disBttns.setPadding(new Insets(5));

		// Building grid layout
		atmDisplay.add(lbluserNum, 0, 0);
		atmDisplay.add(tfuserInput, 0, 1);
		atmDisplay.add(disBttns, 0, 2);
		atmDisplay.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(2));
		tfuserInput.setStyle("-fx-font-style: italic");
	}

	// Override registerEventHandlers to handle numeric button presses
	@Override
	protected void registerEventHandlers() {
		ArrayList<Button> currList = (copyListButtons != null) ? copyListButtons : listButtons;
		for (Button btn : currList) {
			// New!
			// Only assign actions for numeric buttons (0-9)
			if (btn.getText().matches("\\d")) {  // Check if button text is a digit
				btn.setOnAction(e -> tfuserInput.appendText(btn.getText()));
			}
		}
	}
}
