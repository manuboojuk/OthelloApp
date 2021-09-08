package ca.utoronto.utm.othello.viewcontroller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;

import ca.utoronto.utm.othello.model.*;

/**
 * Updates the timer every second. When either timer reaches 0, the buttons on the board disable.
 * 
 * @author nellippi
 *
 */
public class CTimerEventHandler implements EventHandler<ActionEvent> {
	
	private Seconds p1sec;
	private Seconds p2sec;
	private Label p1time;
	private Label p2time;
	private Othello o;
	private VGameStatus gameStatus;
	private VBoard board;

	public CTimerEventHandler(Seconds p1sec, Seconds p2sec, Label p1time, Label p2time, Othello o,
			VGameStatus gameStatus, VBoard vBoard) {
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.p1time = p1time;
		this.p2time = p2time;
		this.o = o;
		this.gameStatus = gameStatus;
		this.board = vBoard;
	}

	public void handle(ActionEvent event) {
		if (p1sec.sec == 0 || p2sec.sec == 0) {
			ObservableList<Node> lst = this.board.getChildren();
			for (int i = 0; i < lst.size(); i++) {
				lst.get(i).setDisable(true);
			}
			if (p1sec.sec == 0) {
				this.gameStatus.setText("●:" + o.getCount(OthelloBoard.P1) +
						 " ○:" + o.getCount(OthelloBoard.P2) + "    " + "○️ wins!");
			}
			else {
				this.gameStatus.setText("●:" + o.getCount(OthelloBoard.P1) +
						 " ○️:" + o.getCount(OthelloBoard.P2) + "    " + "● wins!");
			}
		}
		else if (o.getWhosTurn() == OthelloBoard.P1) {
			p1sec.sec--;
			p1time.setText(p1sec.toString(OthelloBoard.P1));
		}
		else if (o.getWhosTurn() == OthelloBoard.P2) {
			p2sec.sec--;
			p2time.setText(p2sec.toString(OthelloBoard.P2));
		}
		else {}
	}
}
