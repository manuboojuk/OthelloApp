package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 * Displays the user a hint for the current move.
 * 
 * @author bhojakma
 *
 */
public class CHintEventHandler implements EventHandler<ActionEvent>{
	private Label hintLabel;
	Othello othello;
	private VBoard vboard;
	
	public CHintEventHandler(Othello othello, Label hintLabel, VBoard vboard) {
		this.hintLabel = hintLabel;
		this.othello = othello;
		this.vboard = vboard;
	}

	public void handle(ActionEvent event) {
		if (othello.alwaysShowHint || othello.isGameOver()) {
			this.hintLabel.setText("(row, col)");
			return;
		}
		Move hint = othello.getHint();
		String hintString = "(" + hint.getRow() + ", " + hint.getCol() + ")";
		this.hintLabel.setText(hintString);
		othello.hint = hint;
		this.vboard.update(othello);
		// converting from a 2-D index to a 1-D index
		// int oneDimentionalIndex = ((hint.getRow() + 1) * Othello.DIMENSION) - (Othello.DIMENSION - hint.getCol());
		// this.vboard.getChildren().get(oneDimentionalIndex).setStyle("-fx-inner-border: #DC143C");
	}
}
