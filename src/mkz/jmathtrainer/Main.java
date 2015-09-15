/**
 * JavaMathTrainer -- Main.java
 * 
 * www.GitHub.com/markoczy/JavaMathTrainer
 * 
 * @author Aleistar Markóczy (amark)
 */
package mkz.jmathtrainer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Main class that holds the JFX Conform entry point
 */
public class Main extends Application {

	/**
	 * Start the application loop
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("ui/option/GUI_Option.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setTitle("Java math trainer");
			primaryStage.setScene(scene);
			// primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Enty point");
		launch(args);
	}
}
