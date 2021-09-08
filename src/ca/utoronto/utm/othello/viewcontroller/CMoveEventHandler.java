package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Label;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;

/**
 * Makes a move depending on the clicked token.
 * 
 * @author lamadri2
 *
 */

public class CMoveEventHandler implements EventHandler<ActionEvent> {
	
	private int row;
	private int col;
	private Othello othello;
	private ArrayList<Othello> saveMoves;
	private ArrayList<ArrayList<Integer>> saveTimer;
	private Seconds p1sec;
	private Seconds p2sec;
	private Label hintLabel;
	
	public CMoveEventHandler(int row, int col, Othello othello, ArrayList<Othello> saveScenes,
			ArrayList<ArrayList<Integer>> saveTimer, Seconds p1sec, Seconds p2sec, Label hintLabel) {
		this.row = row;
		this.col = col;
		this.othello = othello;
		this.saveMoves = saveScenes;
		this.saveTimer = saveTimer;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.hintLabel = hintLabel;
	}

	public void handle(ActionEvent event) {
		if (othello.alwaysShowHint == false) {
			this.hintLabel.setText("(row, col)");
		}
		if (othello.isGameOver()) {
			return;
		}
		Othello newOthello = othello.copy();
		Othello checkMove = othello.copy();
		if (checkMove.move(row, col)) {
			saveMoves.add(newOthello);
		}
		ArrayList<Integer> times = new ArrayList<Integer>();
		times.add(p1sec.sec);
		times.add(p2sec.sec);
		saveTimer.add(times);
		othello.move(this.row, this.col);
		

		// opponent's move
		if (Opponent.getInstance().opponent.startsWith("Human")) {
			return;
		}
		
		PlayerGreedy greedy = new PlayerGreedy(othello, 'X');
		PlayerRandom random = new PlayerRandom(othello, 'X');
		PlayerAdvanced advanced = new PlayerAdvanced(othello, 'x');
		if (PlayerColour.getInstance().colour == "black") {
			greedy = new PlayerGreedy(othello, 'O');
			random = new PlayerRandom(othello, 'O');
			advanced = new PlayerAdvanced(othello, 'O');
		}
		if (Opponent.getInstance().opponent.startsWith("Greedy")) {
			Move move = greedy.getMove();
			if (move == null) {
				return;
			}
			else {
				othello.move(move.getRow(), move.getCol());
			}
		}
		else if (Opponent.getInstance().opponent.startsWith("Random")) {
			Move move = random.getMove();
			if (move == null) {
				return;
			}
			else {
				othello.move(move.getRow(), move.getCol());
			}
		}
		else if (Opponent.getInstance().opponent.startsWith("Advanced")) {
			Move move = advanced.getMove();
			if (move == null) {
				return;
			}
			else {
				othello.move(move.getRow(), move.getCol());
			}
		}
		
		return;
	}
}