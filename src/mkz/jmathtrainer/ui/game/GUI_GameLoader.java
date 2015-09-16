package mkz.jmathtrainer.ui.game;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mkz.jmathtrainer.core.config.GameConfig;
import mkz.jmathtrainer.ui.game.stats.GUI_StatsController;

/**
 * The Class GUI_GameLoader. Loads the game and shows
 * the game stats when finished
 */
public class GUI_GameLoader 
{
	
	/**
	 * Start a new new game.
	 * 
	 * TODO Get stage from main application
	 *
	 * @param aConfig the reference config
	 */
	public void newGame(GameConfig aConfig)
	{
		Stage stage = new Stage();
		stage.setTitle("Java Math trainer");
		
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("GUI_Game.fxml").openStream());
			GUI_GameController controller = (GUI_GameController)loader.getController();
	        controller.init(aConfig,this);
//			controller.resetSelection();
			
			stage.setScene(new Scene(root));
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
			{
		          public void handle(WindowEvent we) {
		        	  controller.finishGame(true);
		          }
		    });       
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        
	}
	
	/**
	 * Show game statistics.
	 *
	 * @param playtime the playtime
	 * @param points the points
	 * @param good the good
	 * @param bad the bad
	 */
	public void showStats(int playtime,int points, int good, int bad)
	{
		Stage stage = new Stage();
		stage.setTitle("Game over!");
		
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			BorderPane root = loader.load(getClass().getResource("stats/GUI_Stats.fxml").openStream());
			
			stage.setScene(new Scene(root));
	        stage.show();
	        
	        GUI_StatsController controller = (GUI_StatsController)loader.getController();
	        controller.init(playtime,points,good,bad);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	// TODO: GetResults
}
