package PAssign07;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PAssign07 extends Application {
	
	@Override
	public void start(Stage stage) {
		// OK COOL AF!!!!!!!!!!!!!!
		// Creating a Instance of Custom Class
		BaiKeyPad keyPad = new BaiKeyPad();
	
		// Setting the Scene using the Custom Instance.
		Scene scene = new Scene(keyPad.atmDisplay, 200, 200);
		// Want to Give a Grey area for the scene
		keyPad.atmDisplay.setStyle("-fx-background-color: grey;");

		
		//Building out Stage
		stage.setTitle("PAssign07");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}