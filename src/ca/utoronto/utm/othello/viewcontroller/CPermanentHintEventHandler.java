package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Displays a hint every turn when permanent hint is checked.
 * 
 * @author bhojakma
 *
 */

public class CPermanentHintEventHandler implements EventHandler<ActionEvent>{
	
	private Label hintLabel;
	Othello othello;
	private VBoard vboard;
	
	public CPermanentHintEventHandler(Othello othello, Label hintLabel, VBoard vboard) {
		this.hintLabel = hintLabel;
		this.othello = othello;
		this.vboard = vboard;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		CheckBox checkBox = (CheckBox) event.getSource();
		this.othello.alwaysShowHint = checkBox.isSelected();
		if (checkBox.isSelected()) {
			Move hint = othello.getHint();
			String hintString = "(" + hint.getRow() + ", " + hint.getCol() + ")";
			this.hintLabel.setText(hintString);
			othello.hint = hint;
			this.vboard.update(othello);
		}
	}

}
