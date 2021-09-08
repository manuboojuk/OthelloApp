package ca.utoronto.utm.othello.viewcontroller;



import java.util.ArrayList;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Displays animation after a move is made.
 * 
 * @author lamadri2
 *
 */
public class FadeInEventHandler implements EventHandler<ActionEvent> {

	private FadeTransition fadeIn;
	private VBoard vBoard;
	private Othello othello;
	private ArrayList<Othello> saveScenes; 
	private ArrayList<ArrayList<Integer>> saveTimer;
	private Seconds p1sec;
	private Seconds p2sec;
	private Label hintLabel;
	
	public FadeInEventHandler(FadeTransition fadeIn, VBoard vBoard, Othello othello, ArrayList<Othello> saveScenes, 
			ArrayList<ArrayList<Integer>> saveTimer, Seconds p1sec, Seconds p2sec, Label hintLabel) {
		this.fadeIn = fadeIn;
		this.vBoard = vBoard;
		this.othello = othello;
		this.saveScenes = saveScenes;
		this.saveTimer = saveTimer;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.hintLabel = hintLabel;
	}
	
	@Override
	public void handle(ActionEvent event) {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(250), vBoard);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		Move hint = othello.getHint();
		// this.getChildren().clear();
		// Move hint = othello.getHint();
		for(int row = 0; row <= 7; row ++) {
			for(int col = 0; col <= 7; col ++) {
				 Button button = new Button();
				 button.setFont(Font.font("Monospaced", 20));
				 if ((othello.board.board[row][col]) == 'X'){
					button.setStyle("-fx-background-color: #000000");
				 } else if ((othello.board.board[row][col]) == 'O'){
					button.setStyle("-fx-background-color: #ffffff");
				 }
				 
				 // Highlights buttons that can be moved to 
				 OthelloBoard copyOthello = othello.board.copy();
					if (copyOthello.move(row, col, othello.getWhosTurn())) {
						button.setStyle("-fx-inner-border: #0000FF");
						copyOthello = othello.board.copy();
					}
				 if (othello.isGameOver() == false && othello.alwaysShowHint && hint != null && hint.getRow() == row && hint.getCol() == col) {
					 button.setStyle("-fx-inner-border: #DC143C");
					 othello.hint = null;
					 hintLabel.setText("(" + hint.getRow() + ", " + hint.getCol() + ")");
				 }
				 else if (othello.isGameOver() == false && othello.alwaysShowHint == false && othello.hint != null && othello.hint.getRow() == row && othello.hint.getCol() == col) {
					 button.setStyle("-fx-inner-border: #DC143C");
					 hintLabel.setText("(" + othello.hint.getRow() + ", " + othello.hint.getCol() + ")");
					 othello.hint = null;
				 }
				 if (othello.isGameOver()) {
					hintLabel.setText("(row, col)");
				 }
				 double r=20;
				 button.setShape(new Circle(r));
				 button.setMinSize(2*r, 2*r);
				 button.setMaxSize(2*r, 2*r);
				 
			     vBoard.add(button, col, row, 1, 1);
			     button.setOnAction(new CMoveEventHandler(row, col, othello, saveScenes, saveTimer, p1sec, p2sec, hintLabel));
			     
			}
		}
		othello.hint = null;
		fadeIn.play();
	}

}
