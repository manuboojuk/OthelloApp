package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;

/**
 * Resets the board and timer to the previous user move.
 * 
 * @author kamathya
 *
 */

public class CUndoEventHandler implements EventHandler<ActionEvent> {

	private VBoard vBoard;
	private Othello othello;
	private ArrayList<Othello> saveScenes;
	private VGameStatus vGameStatus;
	private ArrayList<ArrayList<Integer>> saveTimer;
	private Seconds p1sec;
	private Seconds p2sec;
	private Timer timer;
	private Label p1time;
	private Label p2time;
	private int index;
	private CHintEventHandler hintHandler;
	private CHintTypeEventHandler hintTypeHandler;
	private Seconds newp1sec;
	private Seconds newp2sec;
	private CPermanentHintEventHandler permanentHintHandler;
	private Label hintLabel;
	
	public CUndoEventHandler(ArrayList<Othello> saveScenes, Othello othello, VBoard vBoard, VGameStatus vGameStatus,
			ArrayList<ArrayList<Integer>> saveTimer, Seconds p1sec, Seconds p2sec, Label p1time, Label p2time,
			Timer timer, int index, CHintEventHandler hintHandler, CHintTypeEventHandler hintTypeHandler,
			Seconds newp1sec, Seconds newp2sec, CPermanentHintEventHandler permanentHintHandler, Label hintLabel) {
		this.saveScenes = saveScenes;
		this.othello = othello;
		this.vBoard = vBoard;
		this.vGameStatus = vGameStatus;
		this.saveTimer = saveTimer;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.timer = timer;
		this.p1time = p1time;
		this.p2time = p2time;
		this.index = index;
		this.hintHandler = hintHandler ;
		this.hintTypeHandler = hintTypeHandler;
		this.newp1sec = newp1sec;
		this.newp2sec = newp2sec;
		this.permanentHintHandler = permanentHintHandler;
		this.hintLabel = hintLabel;
	}

	public void handle(ActionEvent event) {
		if (othello.alwaysShowHint == false) {
			this.hintLabel.setText("(row, col)");
		}
		if (saveScenes.size() > 0) {
			timer.stop();
			if (index == 2) {
				saveTimer.remove(saveTimer.size() - 1);
				index--;
			}
			Othello oldMove = saveScenes.get(saveScenes.size() - 1);
			if (saveTimer.size() == 0) {
				if (newp1sec.sec < 0) {
					p1sec.sec = 300;
				}
				else {
					p1sec.sec = newp1sec.sec;
				}
				if (newp2sec.sec < 0) {
					p2sec.sec = 300;
				}
				else {
		    		p2sec.sec = newp2sec.sec;
				}
			}
			else {
				ArrayList<Integer> oldTime = saveTimer.get(saveTimer.size() - 1);
				p1sec.sec = oldTime.get(0);
				p2sec.sec = oldTime.get(1);
				saveTimer.remove(saveTimer.size() - 1);
			}
			boolean alwaysShowHint = othello.alwaysShowHint;
			othello = oldMove;
			othello.alwaysShowHint = alwaysShowHint;
			hintHandler.othello = this.othello;
			hintTypeHandler.othello = this.othello;
			permanentHintHandler.othello = this.othello;
			saveScenes.remove(saveScenes.size() - 1);
			
			Move hint = othello.getHint();
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
					 if (othello.alwaysShowHint && hint.getRow() == row && hint.getCol() == col) {
						button.setStyle("-fx-inner-border: #DC143C");
						othello.hint = null;
						hintLabel.setText("(" + hint.getRow() + ", " + hint.getCol() + ")");
					 }
						 
					 if (othello.hint != null && othello.hint.getRow() == row && othello.hint.getCol() == col) {
						button.setStyle("-fx-inner-border: #DC143C");
						hintLabel.setText("(" + othello.hint.getRow() + ", " + othello.hint.getCol() + ")");
						othello.hint = null;
					 }
					 
					 double r = 20;
					 button.setShape(new Circle(r));
					 button.setMinSize(2*r, 2*r);
					 button.setMaxSize(2*r, 2*r);
					 vBoard.add(button, col, row, 1, 1);
				     button.setOnAction(new CMoveEventHandler(row, col, othello, saveScenes, saveTimer, p1sec, p2sec, hintLabel));
				}
			}
			String strPlayer;
			if (othello.getWhosTurn() == 'X') {
				strPlayer = "●";
			}
			else {
				strPlayer = "○";
			}
			vGameStatus.setText("●:" + othello.getCount(OthelloBoard.P1) + " ○:" + othello.getCount(OthelloBoard.P2) + "    "
			+ strPlayer + " moves next");
			this.p1time.setText(p1sec.toString(OthelloBoard.P1));
			this.p2time.setText(p2sec.toString(OthelloBoard.P2));
			timer.timertask = new CTimerEventHandler(p1sec, p2sec, p1time, p2time, othello, vGameStatus, vBoard);
			timer.keyframe = new KeyFrame(Duration.seconds(1), timer.timertask);
			timer.timer = new Timeline(timer.keyframe);
			timer.start();
		}
		else {
			index = 2;
		}
		othello.attach(vBoard);
		othello.attach(vGameStatus);
		}
	}
