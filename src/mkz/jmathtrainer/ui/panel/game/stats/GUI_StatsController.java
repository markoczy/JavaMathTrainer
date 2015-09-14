package mkz.jmathtrainer.ui.panel.game.stats;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mkz.jmathtrainer.util.NumberTools;

public class GUI_StatsController 
{
	@FXML
	private Label lblTotalTime;

	@FXML
	private Label lblScore;
	
	@FXML
	private Label lblGoodAmount;

	@FXML
	private Label lblBadAmount;

	@FXML
	private Label lblEfficiency;
	
	@FXML
	private Button btnFinish;
	
	Integer mPlayTime=0;
	Integer mPoints=0;
	Integer mGood=0;
	Integer mBad=0;
	
	public void init(int playtime,int points, int good, int bad)
	{
		mPlayTime=playtime;
		mPoints=points;
		mGood=good; 
		mBad=bad; 
		
		updateGUI();
	}
	
	
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
	
	@FXML
	public void btnFinish_Clicked()
	{
		Stage stage = (Stage) btnFinish.getScene().getWindow();
		
	    stage.close();
	}
	
}
