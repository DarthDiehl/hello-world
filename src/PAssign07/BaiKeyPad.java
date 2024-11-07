package PAssign07;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BaiKeyPad extends KeyPadPane{
	protected Label lbluserNum;
	protected TextField tfuserInput;
	protected GridPane atmDisplay = new GridPane();
	protected Button btnEnter = new Button("ENTER");
	protected Button btnClear = new Button("CLEAR");
	protected Button btnBlank3 = new Button("");
	protected HBox disBttns = new HBox(5);
	protected VBox opBttns = new VBox(2);
	
	// Added a Icon to this Button = HELL!
	protected Button btnCancel = new Button("");
	protected ImageView backSpace = new ImageView(new Image(getClass().getResource("/image/backspace.png").toExternalForm()));

	public BaiKeyPad() {
		// Extra parts Specific to this File.
		lbluserNum = new Label("Enter PIN:");
		tfuserInput = new TextField();
		tfuserInput.setPromptText("PIN");

		// Styling
		btnEnter.setStyle("-fx-background-color: green;");
		btnCancel.setStyle("-fx-background-color: yellow;");
		btnClear.setStyle("-fx-background-color: red;");
		// Binding Button Size.
		btnBlank3.prefWidthProperty().bind(btnEnter.widthProperty());
		btnBlank3.prefHeightProperty().bind(btn0.heightProperty());
		btnClear.prefWidthProperty().bind(btnEnter.widthProperty());
		btnClear.prefHeightProperty().bind(btn0.heightProperty());
		// Just for the BackSpace Button
		btnCancel.prefWidthProperty().bind(btnEnter.widthProperty());
		btnCancel.prefHeightProperty().bind(btn0.heightProperty());
		backSpace.fitWidthProperty().bind(btnEnter.widthProperty());
		backSpace.fitHeightProperty().bind(btn0.heightProperty());
		backSpace.setPreserveRatio(true);
		// add Icon to Button
		btnCancel.setGraphic(backSpace);

		
		// Erases all contents in tfuserInput - Works
		btnClear.setOnAction(e -> tfuserInput.clear());

		// Trim the last number entered in Pin - Works
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

		// Want this to check that a valid pin is entered.
		btnEnter.setOnAction(e -> {
			try {
				String userInputCopy2 = tfuserInput.getText();
				if(!userInputCopy2.isEmpty() && userInputCopy2.length() == 4) {
					tfuserInput.setText("Welcome - PIN is Accepted");
					
					// Produces a puppy pic. ur welcome.
					Stage stage1 = new Stage();
					Pane puppyPane = new Pane();
					ImageView puppy = new ImageView(new Image(getClass().getResource("/image/backspace.png").toExternalForm()));
					puppyPane.getChildren().add(puppy);
					Scene scene1 = new Scene(puppyPane);
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
}
