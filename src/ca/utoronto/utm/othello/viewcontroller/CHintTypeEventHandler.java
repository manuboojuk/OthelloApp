package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

/**
 * Changes the hint type (random, greedy, advanced)
 * 
 * @author bhojakma
 *
 */

public class CHintTypeEventHandler implements EventHandler<ActionEvent>{
	Othello othello;
	public CHintTypeEventHandler(Othello othello) {
		this.othello = othello;
	}
	
	@Override
	public void handle(ActionEvent event) {
		RadioButton selectedHint = (RadioButton) event.getSource();
		this.othello.hintType = selectedHint.getText();
	}

}
