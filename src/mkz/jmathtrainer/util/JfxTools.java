/**
 * JfxTools.java
 * 
 * @author Aleistar Markoczy
 */
package mkz.jmathtrainer.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Static JFX functions
 */
public class JfxTools 
{

	/**
	 * Show a information dialog box.
	 *
	 * @param title the title
	 * @param header the header
	 * @param content the content
	 */
	public static void showInformationDialogBox(String title, String header, String content)
	{
		showDialogBox(title, header, content, AlertType.INFORMATION);
	}
	
	/**
	 * Show a dialog box.
	 *
	 * @param title the title
	 * @param header the header
	 * @param content the content
	 * @param type the alert type
	 */
	private static void showDialogBox(String title, String header, String content, AlertType type)
	{
		Alert alert = new Alert(type);
		
		if(title!=null) alert.setTitle(title);
		if(header!=null) alert.setHeaderText(header);
		if(content!=null) alert.setContentText(content);
		
		alert.showAndWait();
	}
	
}
