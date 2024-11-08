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

public class BaisPad extends KeyPadPane {
    protected Label lbluserNum;
    protected TextField tfuserInput;
    protected Button btnEnter = new Button("ENTER");
    protected Button btnClear = new Button("CLEAR");
    protected Button btnCancel = new Button("");
    protected ImageView backSpace = new ImageView(new Image(getClass().getResource("/image/backspace.png").toExternalForm()));
    
    protected VBox opBttns = new VBox(2);
    protected HBox disBttns = new HBox(5);
    protected GridPane atmDisplay = new GridPane();

    public BaisPad() {
        // Initialize label and text field
        lbluserNum = new Label("Enter PIN:");
        tfuserInput = new TextField();
        tfuserInput.setPromptText("PIN");

        // Styling
        btnEnter.setStyle("-fx-background-color: green;");
        btnCancel.setStyle("-fx-background-color: yellow;");
        btnClear.setStyle("-fx-background-color: red;");
        btnCancel.setGraphic(backSpace);
        
        // Set button dimensions
        double buttonWidth = 60;
        double buttonHeight = 20;

        btnEnter.setPrefSize(buttonWidth, buttonHeight);
        btnCancel.setPrefSize(buttonWidth, buttonHeight);
        btnClear.setPrefSize(buttonWidth, buttonHeight);

        // Resize and style the backspace image
        backSpace.setFitWidth(buttonWidth * 0.6); 
        backSpace.setFitHeight(buttonHeight * 0.6);

        // Define actions for control buttons
        btnClear.setOnAction(e -> tfuserInput.clear());
        btnCancel.setOnAction(e -> handleBackspace());
        btnEnter.setOnAction(e -> checkPin());

        // Customize the event handlers for numeric buttons from KeyPadPane
        registerEventHandlers();

        // Layout setup
        setupLayout();
    }

    // Method to handle backspace (delete last character)
    private void handleBackspace() {
        String text = tfuserInput.getText();
        if (!text.isEmpty()) {
            tfuserInput.setText(text.substring(0, text.length() - 1));
        }
    }

    // Method to check if PIN is valid
    private void checkPin() {
        String pin = tfuserInput.getText();
        if (pin.length() == 4) {
            tfuserInput.setText("Welcome - PIN is Accepted");
            
            // Optional: Show puppy image as a fun confirmation
            Stage stage1 = new Stage();
            Pane puppyPane = new Pane();
            ImageView puppy = new ImageView(new Image(getClass().getResource("/image/puppy.png").toExternalForm()));
            puppyPane.getChildren().add(puppy);
            Scene scene1 = new Scene(puppyPane, 200, 200);
            stage1.setTitle("Puppy!!!");
            stage1.setScene(scene1);
            stage1.show();
        } else {
            tfuserInput.setText("Error - Only 4 Digits");
        }
    }

    // Override registerEventHandlers to handle numeric button presses
    @Override
    protected void registerEventHandlers() {
        ArrayList<Button> currList = (copyListButtons != null) ? copyListButtons : listButtons;

        for (Button btn : currList) {
            // Only assign actions for numeric buttons (0-9)
            if (btn.getText().matches("\\d")) {  // Check if button text is a digit
                btn.setOnAction(e -> tfuserInput.appendText(btn.getText()));
            }
        }
    }

    // Layout setup to arrange all UI elements
    private void setupLayout() {
        // Add control buttons to VBox and HBox
        opBttns.getChildren().addAll(btnEnter, btnCancel, btnClear);
        disBttns.getChildren().addAll(this, opBttns);  // 'this' refers to KeyPadPane layout

        // Arrange all components in atmDisplay
        atmDisplay.add(lbluserNum, 0, 0);
        atmDisplay.add(tfuserInput, 0, 1);
        atmDisplay.add(disBttns, 0, 2);
        atmDisplay.setAlignment(Pos.CENTER);
        atmDisplay.setPadding(new Insets(10));
//      change to work for puppy pic.  
//		backSpace.fitWidthProperty().bind(btnEnter.widthProperty());
//		backSpace.fitHeightProperty().bind(btn0.heightProperty());
    }
}