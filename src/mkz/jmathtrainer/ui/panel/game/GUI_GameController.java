package mkz.jmathtrainer.ui.panel.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import mkz.jmathtrainer.core.config.GameConfig;
import mkz.jmathtrainer.core.equation.Equation;
import mkz.jmathtrainer.core.equation.EquationFactory;
import mkz.jmathtrainer.util.IO;
import mkz.jmathtrainer.util.NumberTools;

public class GUI_GameController 
{
	
	//////////////// FXML :: Members ////////////////
	
	@FXML
	private TextField tfdOutputEquation;
	
	@FXML
	private TextField tfdInputValue;
	
	@FXML
	private Label lblRemainingTime; 
	
	@FXML
	private Label lblCurrentRound; 
	
	@FXML
	private Label lblGoodCount; 
	
	@FXML
	private Label lblBadCount; 
	
	@FXML
	private Label lblPointsCount; 
	
	@FXML
	private Label lblLastEvent;
	
	@FXML
	private Button btnEnterValue;
	
	//////////////// Controller :: Members ////////////////
	
	GameConfig mGameConfig=null;
	GUI_GameLoader mLoader=null;
	
	Equation mEquation=null;
	
	Integer mGameTime = 0;
	Integer mCurrentRound = 1;
	Integer mCurrentPointsAmount = 0;
	Integer mCurrentGoodAmount = 0;
	Integer mCurrentFailedAmount = 0;
	String mLastEvent = LastEvent.NULL;
	
	Integer mPointMultiplicator=100;
	
	Boolean mGameRunning=false;
	
	//////////////// FXML :: Methods ////////////////
	
	@FXML
	public void btnEnterValue_Clicked()
	{
		IO.SysOutD("Enter button pressed");
		
		if(checkUserInput())
		{
			mCurrentGoodAmount++;
			mLastEvent=LastEvent.GOOD;
			mCurrentPointsAmount+=mPointMultiplicator;
		}
		else
		{
			mCurrentFailedAmount++;
			mLastEvent=LastEvent.BAD;
			mGameTime-=10;
		}
		
		mCurrentRound++;
		tfdInputValue.setText("");
		
		newEquation();
		updateGUI();
	}
	
	@FXML
	public void tfdInputValue_handleEnterPressed(KeyEvent event)
	{
	    if (event.getCode() == KeyCode.ENTER) 
	    {
	    	btnEnterValue_Clicked();
	    }
	}
	
	//////////////// Controller :: Methods ////////////////
	
	public void init(GameConfig aGameConfig,GUI_GameLoader aLoader)
	{
		 init_tfdInputValue();
		 
		 mGameConfig=aGameConfig;
		 mLoader=aLoader;
		 
		 mGameTime=aGameConfig.timeInSeconds;
		 
		 switch(aGameConfig.difficulty)
		 {
		 case EASY:
			 mPointMultiplicator=100;
			 break;
		 case MEDIUM:
			 mPointMultiplicator=200;
			 break;
		 case HARD:
			 mPointMultiplicator=300;
			 break;
		 }
		 
		 mGameRunning=true;
		 
		 newEquation();
		 updateGUI();
		 loopTimer(++mGameTime);
	}
	
	public void loopTimer(int seconds)
	{
		if(mGameRunning)
		{
			if(mGameTime>0)
			{
				mGameTime--;
				lblRemainingTime.setText(mGameTime.toString());
				
				Timeline timeline = new Timeline(new KeyFrame(
				        Duration.millis(1000),
				        ae -> loopTimer(mGameTime)));
				
				timeline.play();
			}
			else
			{
				finishGame(false);
			}
		
		}
	}
	
	public void finishGame(boolean interrupted)
	{
		IO.SysOutD("Game finished, interrupted = "+interrupted);
		
		int gameTime=interrupted ? mGameConfig.timeInSeconds-mGameTime : mGameConfig.timeInSeconds;
		
		mLoader.showStats(gameTime, mCurrentPointsAmount, mCurrentGoodAmount, mCurrentFailedAmount);
	
		mGameRunning=false;
		
		Stage stage = (Stage) btnEnterValue.getScene().getWindow();
		
	    stage.close();
	}
	
	public void resetSelection()
	{
		tfdInputValue.requestFocus();
	}
	
	public void init_tfdInputValue()
	{
		tfdInputValue.textProperty().addListener(new ChangeListener<String>() 
		{
		    @Override 
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
		    {
		    	
		    	boolean match = NumberTools.isDoubleNumber(newValue);
		    	
		    	if(!newValue.equals(""))
		    	{
			    	if(!match)
			    	{
			    		tfdInputValue.setText(oldValue);
			    	}
		    	}
		    	
		    }
		});
	}
	
	public boolean checkUserInput()
	{
		try
		{
			String sValue=null;
			
			if(tfdInputValue.getText().equals(""))
			{
				sValue="0";
			}
			else
			{
				sValue=tfdInputValue.getText();
			}
			
			double value = Double.parseDouble(sValue);
			
			return value==mEquation.value;
		}
		catch(Exception e)
		{
			IO.SysOutD("Exception while parsing user input: "+e.getMessage());
			return false;
		}
	}

	public void newEquation()
	{
		mEquation = EquationFactory.createEquation(mGameConfig);
		tfdOutputEquation.setText(mEquation.equation);
	}
	
	public void updateGUI()
	{
		lblRemainingTime.setText(mGameTime.toString());
		
		lblCurrentRound.setText(mCurrentRound.toString()); 
		
		lblGoodCount.setText(mCurrentGoodAmount.toString()); 
		
		lblBadCount.setText(mCurrentFailedAmount.toString());
		
		lblPointsCount.setText(mCurrentPointsAmount.toString()); 
		
		lblLastEvent.setText(mLastEvent);
	}
	
	private class LastEvent
	{
		public static final String NULL="<null>";
		public static final String GOOD="right";
		public static final String BAD="wrong";
	}
	
	
}
