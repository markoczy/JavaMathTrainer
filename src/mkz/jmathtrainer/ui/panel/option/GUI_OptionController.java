package mkz.jmathtrainer.ui.panel.option;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import mkz.jmathtrainer.core.config.GameConfig;
import mkz.jmathtrainer.core.config.GameConfig.DifficultyEnum;
import mkz.jmathtrainer.ui.panel.game.GUI_GameLoader;
import mkz.jmathtrainer.util.IO;
import mkz.jmathtrainer.util.JfxTools;

public class GUI_OptionController 
{
	@FXML
    private CheckBox cbxEnableAdd; 
	
	@FXML
    private CheckBox cbxEnableSub; 
	
	@FXML
    private CheckBox cbxEnableMpl; 
	
	@FXML
    private CheckBox cbxEnableDiv; 
	
	@FXML
    private RadioButton rbnDiffEasy; 
	
	@FXML
    private RadioButton rbnDiffMedium; 
	
	@FXML
    private RadioButton rbnDiffHard; 
	
	@FXML
	private TextField tfTimeInSeconds;
	
	private GameConfig mGameConfig=new GameConfig();
	
	private GUI_GameLoader mGameLoader = new GUI_GameLoader();
	
	public void btnStartClicked()
	{
		IO.SysOutD("Button start clicked");
		
		loadGameConfig();
		IO.SysOutD("Config = "+mGameConfig);
		
		boolean opsValid=false;
		boolean allowNewGame=true;
		
		opsValid|=mGameConfig.additionEnabled;
		opsValid|=mGameConfig.subtractionEnabled;
		opsValid|=mGameConfig.divisionEnabled;
		opsValid|=mGameConfig.multiplicationEnabled;
		
		// XXX hard mode nyi: show message
		if(mGameConfig.difficulty==DifficultyEnum.HARD)
		{
			JfxTools.showInformationDialogBox("Apologies", "Under construction.",
					"Sorry, hard mode is not implemented yet...");
			allowNewGame=false;
		}
		
		// No operation selected: show message
		if(!opsValid) 
		{
			JfxTools.showInformationDialogBox("Information", "No operations selected.",
				"Are you trying to cheat? ;-)");
		}
		
		// Start the game
		if(opsValid && allowNewGame)
		{ 
			mGameLoader.newGame(mGameConfig);
		}
		
	}
	
	private void loadGameConfig()
	{
		//// Enabled Operations
		
		mGameConfig.additionEnabled=cbxEnableAdd.selectedProperty().getValue();
		mGameConfig.subtractionEnabled=cbxEnableSub.selectedProperty().getValue();
		mGameConfig.multiplicationEnabled=cbxEnableMpl.selectedProperty().getValue();
		mGameConfig.divisionEnabled=cbxEnableDiv.selectedProperty().getValue();
		
		//// Difficulty
		
		if(rbnDiffEasy.selectedProperty().getValue()==true)
		{
			mGameConfig.difficulty=GameConfig.DifficultyEnum.EASY;
		}
		else if(rbnDiffHard.selectedProperty().getValue()==true)
		{
			mGameConfig.difficulty=GameConfig.DifficultyEnum.HARD;			
		}
		else
		{
			mGameConfig.difficulty=GameConfig.DifficultyEnum.MEDIUM;			
		}

		//// Time
		
		String sTime=tfTimeInSeconds.textProperty().getValue();
		Integer iTime=null;
		
		try
		{
			iTime=Integer.parseInt(sTime);
		}
		catch(Exception e){}
		
		mGameConfig.timeInSeconds = iTime != null ? iTime : 120;
		
	}
}
