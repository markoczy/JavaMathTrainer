package mkz.jmathtrainer.ui.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

/**
 * The Class GUI_GameController.
 */
public class GUI_GameController 
{
	
	//////////////// FXML :: Members ////////////////
	
	/** The tfd output equation. */
	@FXML
	private TextField tfdOutputEquation;
	
	/** The tfd input value. */
	@FXML
	private TextField tfdInputValue;
	
	/** The lbl remaining time. */
	@FXML
	private Label lblRemainingTime; 
	
	/** The lbl current round. */
	@FXML
	private Label lblCurrentRound; 
	
	/** The lbl good count. */
	@FXML
	private Label lblGoodCount; 
	
	/** The lbl bad count. */
	@FXML
	private Label lblBadCount; 
	
	/** The lbl points count. */
	@FXML
	private Label lblPointsCount; 
	
	/** The lbl last event. */
	@FXML
	private Label lblLastEvent;
	
	/** The btn enter value. */
	@FXML
	private Button btnEnterValue;
	
	//////////////// Controller :: Members ////////////////
	
	/** The member game config. */
	GameConfig mGameConfig=null;
	
	/** The member game loader. */
	GUI_GameLoader mLoader=null;
	
	/** The member equation. */
	Equation mEquation=null;
	
	/** The member game time. */
	Integer mGameTime = 0;
	
	/** The member current round. */
	Integer mCurrentRound = 1;
	
	/** The member current points amount. */
	Integer mCurrentPointsAmount = 0;
	
	/** The member current good amount. */
	Integer mCurrentGoodAmount = 0;
	
	/** The member current failed amount. */
	Integer mCurrentFailedAmount = 0;
	
	/** The member last event. */
	String mLastEvent = LastEvent.NULL;
	
	/** The member point multiplicator. */
	Integer mPointMultiplicator=100;
	
	/** The member game running. */
	Boolean mGameRunning=false;
	
	//////////////// FXML :: Methods ////////////////
	
	/**
	 * Btn enter value_ clicked.
	 */
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
	
	/**
	 * TextField input value - handle enter pressed.
	 *
	 * @param event the event
	 */
	@FXML
	public void tfdInputValue_handleEnterPressed(KeyEvent event)
	{
	    if (event.getCode() == KeyCode.ENTER) 
	    {
	    	btnEnterValue_Clicked();
	    }
	}
	
	//////////////// Controller :: Methods ////////////////
	
	/**
	 * Initialise the game controller - Usually done after creating
	 * the Game controler (Attention: if called from outside while GUI is 
	 * running, be sure to wrap this in platform.RunLater)
	 *
	 * @param aGameConfig the a game config
	 * @param aLoader the a loader
	 */
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
		 resetSelection();
	}
	
	/**
	 * Loop the timer.
	 *
	 * @param seconds the seconds
	 */
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
	
	/**
	 * Finish game.
	 *
	 * @param interrupted game interrupted
	 */
	public void finishGame(boolean interrupted)
	{
		IO.SysOutD("Game finished, interrupted = "+interrupted);
		
		int gameTime=interrupted ? mGameConfig.timeInSeconds-mGameTime : mGameConfig.timeInSeconds;
		
		mLoader.showStats(gameTime, mCurrentPointsAmount, mCurrentGoodAmount, mCurrentFailedAmount);
	
		mGameRunning=false;
		
		Stage stage = (Stage) btnEnterValue.getScene().getWindow();
		
	    stage.close();
	}
	
	/**
	 * Reset selection.
	 */
	public void resetSelection()
	{
//		Platform.runLater(new Runnable() {
//		     @Override
//		     public void run() {
//		    	 tfdInputValue.requestFocus();
//		     }
//		});
//		tfdInputValue.requestFocus();
		
		Platform.runLater( new Runnable() {
		    @Override
		    public void run() {
		    	tfdInputValue.requestFocus();
		    }
		});
		
//		tfdInputValue.positionCaret(0);
	}
	
	/**
	 * Init_tfd input value.
	 */
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
	
	/**
	 * Check user input.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * New equation.
	 */
	public void newEquation()
	{
		mEquation = EquationFactory.createEquation(mGameConfig);
		tfdOutputEquation.setText(mEquation.equation);
//		resetSelection();
	}
	
	/**
	 * Update gui.
	 */
	public void updateGUI()
	{
		lblRemainingTime.setText(mGameTime.toString());
		
		lblCurrentRound.setText(mCurrentRound.toString()); 
		
		lblGoodCount.setText(mCurrentGoodAmount.toString()); 
		
		lblBadCount.setText(mCurrentFailedAmount.toString());
		
		lblPointsCount.setText(mCurrentPointsAmount.toString()); 
		
		lblLastEvent.setText(mLastEvent);
	}
	
	/**
	 * The Class LastEvent.
	 */
	private class LastEvent
	{
		
		/** The Constant NULL. */
		public static final String NULL="<null>";
		
		/** The Constant GOOD. */
		public static final String GOOD="right";
		
		/** The Constant BAD. */
		public static final String BAD="wrong";
	}
	
	
}
