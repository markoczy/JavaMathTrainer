package mkz.jmathtrainer.ui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			BorderPane root = FXMLLoader.load(getClass().getResource("panel/option/GUI_Option.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Java math trainer");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	// JFX conform entry point...
	public static void main(String[] args) 
	{
		System.out.println("Enty point");
		launch(args);
	}
}
