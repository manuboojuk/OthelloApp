package ca.utoronto.utm.othello.viewcontroller;

import javafx.scene.control.Label;

/**
 * Class that represents labels that show player status 
 * 
 * @author bhojakma
 *
 */
public class VPlayerStatus extends Label {

	public void update(String buttonPressed) {
		if (PlayerColour.getInstance().colour.endsWith("e")) {
			this.setText("●: " + buttonPressed);
		} else {
			this.setText("○: " + buttonPressed);
		}
	}
}
