package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;

/**
 * Resets the board to the starting board.
 * 
 * @author kamathya
 *
 */
public class CResetEventHandler implements EventHandler<ActionEvent> {

	private Seconds p1sec;
	private Seconds p2sec;
	private Othello othello;
	private VBoard vBoard;
	private ArrayList<Othello> saveScenes;
	private VGameStatus vGameStatus;
	private Timer timer;
	private Label p1time;
	private Label p2time;
	private ArrayList<ArrayList<Integer>> saveTimer;
	private RadioButton humanRadio;
	private COpponentEventHandler opponentHandler;
	private CHintEventHandler hintHandler;
	private CHintTypeEventHandler hintTypeHandler;
	private Seconds newp1sec;
	private Seconds newp2sec;
	private CPermanentHintEventHandler permanentHintHandler;
	private Label hintLabel;
	@SuppressWarnings("unused")
	private int index;

	
	public CResetEventHandler(Othello othello, VBoard vBoard, ArrayList<Othello> saveScenes, VGameStatus vGameStatus,
			Timer timer, Seconds p1sec, Seconds p2sec, Label p1time, Label p2time, ArrayList<ArrayList<Integer>> saveTimer,
			RadioButton humanRadio, COpponentEventHandler opponentHandler, int index, CHintEventHandler hintHandler,
			CHintTypeEventHandler hintTypeHandler, Seconds newp1sec, Seconds newp2sec, CPermanentHintEventHandler 
			permanentHintHandler, Label hintLabel) {
		this.vBoard = vBoard;
		this.othello = othello;
		this.saveScenes = saveScenes;
		this.vGameStatus = vGameStatus;
		this.timer = timer;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.p1time = p1time;
		this.p2time = p2time;
		this.saveTimer = saveTimer;
		this.humanRadio = humanRadio;
		this.opponentHandler = opponentHandler;
		this.hintHandler = hintHandler ;
		this.hintTypeHandler = hintTypeHandler;
		this.index = index;
		this.newp1sec = newp1sec;
		this.newp2sec = newp2sec;
		this.permanentHintHandler = permanentHintHandler;
		this.hintLabel = hintLabel;
	}

	public void handle(ActionEvent event) {
		if (othello.alwaysShowHint == false) {
			this.hintLabel.setText("(row, col)");
		}
		this.humanRadio.setSelected(true);
		vBoard.getChildren().clear();
		timer.stop();
		boolean alwaysShowHint = othello.alwaysShowHint;
		othello = new Othello();
		othello.alwaysShowHint = alwaysShowHint;
		opponentHandler.othello = this.othello;
		hintHandler.othello = this.othello;
		hintTypeHandler.othello = this.othello;
		permanentHintHandler.othello = this.othello;
		saveScenes.clear();
		saveTimer.clear();
		index = 2;
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
		this.p1time.setText(p1sec.toString(OthelloBoard.P1));
		this.p2time.setText(p2sec.toString(OthelloBoard.P2));
		Move hint = othello.getHint();
		for(int row = 0; row <= 7; row ++) {
			for(int col = 0; col <= 7; col ++) {
				Button button = new Button();
				button.setFont(Font.font("Monospaced", 20));
				
				if (row==3&&col==3||row==4&&col==4){
					button.setStyle("-fx-background-color: #000000");
				} else if (row==3&&col==4||row==4&&col==3){
					button.setStyle("-fx-background-color: #ffffff");	
				}
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
				
				double r=20;
				button.setShape(new Circle(r));
				button.setMinSize(2*r, 2*r);
				button.setMaxSize(2*r, 2*r);
				
			    vBoard.add(button, col, row, 1, 1);
			    button.setOnAction(new CMoveEventHandler(row, col, othello, saveScenes, saveTimer, p1sec, p2sec, hintLabel));
			    
			}
		}
		
		vGameStatus.setText("●:2" + " ○:2" + "    ● moves next");
		timer.timertask = new CTimerEventHandler(p1sec, p2sec, p1time, p2time, othello, vGameStatus, vBoard);
		timer.keyframe = new KeyFrame(Duration.seconds(1), timer.timertask);
		timer.timer = new Timeline(timer.keyframe);
		timer.start();
		othello.attach(vBoard);
		othello.attach(vGameStatus);
		}
}
