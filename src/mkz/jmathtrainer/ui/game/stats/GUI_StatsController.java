package mkz.jmathtrainer.ui.game.stats;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mkz.jmathtrainer.util.NumberTools;

/**
 * The Class GUI_StatsController.
 */
public class GUI_StatsController 
{
	
	/** The Label total time. */
	@FXML
	private Label lblTotalTime;

	/** The Label score. */
	@FXML
	private Label lblScore;
	
	/** The Label good amount. */
	@FXML
	private Label lblGoodAmount;

	/** The Label bad amount. */
	@FXML
	private Label lblBadAmount;

	/** The Label efficiency. */
	@FXML
	private Label lblEfficiency;
	
	/** The Button finish. */
	@FXML
	private Button btnFinish;
	
	/** The member play time. */
	Integer mPlayTime=0;
	
	/** The member points. */
	Integer mPoints=0;
	
	/** The member good. */
	Integer mGood=0;
	
	/** The member bad. */
	Integer mBad=0;
	
	/**
	 * Initialise the stats controller - Usually done after creating
	 * or resetting
	 *
	 * @param playtime the playtime
	 * @param points the points
	 * @param good the good
	 * @param bad the bad
	 */
	public void init(int playtime,int points, int good, int bad)
	{
		mPlayTime=playtime;
		mPoints=points;
		mGood=good; 
		mBad=bad; 
		
		updateGUI();
	}
	
	
	/**
	 * Update gui.
	 */
	private void updateGUI()
	{
		lblTotalTime.setText(mPlayTime.toString());
		lblScore.setText(mPoints.toString());
		lblGoodAmount.setText(mGood.toString());
		lblBadAmount.setText(mBad.toString());
		
		Double val= (double)mPoints /(double)mPlayTime;
		val=NumberTools.round(val, 2);
		lblEfficiency.setText(val.toString());
	}
	
	/**
	 * Btn finish_ clicked.
	 */
	@FXML
	public void btnFinish_Clicked()
	{
		Stage stage = (Stage) btnFinish.getScene().getWindow();
		
	    stage.close();
	}
	
}
