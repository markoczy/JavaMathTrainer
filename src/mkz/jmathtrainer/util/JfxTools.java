package mkz.jmathtrainer.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JfxTools 
{

	public static void showInformationDialogBox(String title, String header, String content)
	{
		showDialogBox(title, header, content, AlertType.INFORMATION);
	}
	
	private static void showDialogBox(String title, String header, String content, AlertType type)
	{
		Alert alert = new Alert(type);
		
		if(title!=null) alert.setTitle(title);
		if(header!=null) alert.setHeaderText(header);
		if(content!=null) alert.setContentText(content);
		
		alert.showAndWait();
	}
	
}
