package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.*;
import javafx.scene.control.Label;

/**
 * View Class for game status and number of tokens for each player.
 * 
 * @author kamathya
 *
 */
public class VGameStatus extends Label implements Observer {

	public void update(Observable o) {
		Othello othello = (Othello) o;
		if (othello.isGameOver()) {
			if (othello.getWinner() == OthelloBoard.EMPTY) {
				this.setText("●:" + othello.getCount(OthelloBoard.P1) +
						 " ○:" + othello.getCount(OthelloBoard.P2) + "    Draw!");
			}
			else {
				String player;
				if (othello.getWinner()=='X') {
					player = "●";
				}
				else {
					player = "○";
				}
				this.setText("●:" + othello.getCount(OthelloBoard.P1) +
						 " ○:" + othello.getCount(OthelloBoard.P2) + "    " + player + " wins!");
			}
		}
		else {
			String player;
			if (othello.getWhosTurn()=='X') {
				player = "●";
			}
			else {
				player = "○";
			}
			this.setText("●:" + othello.getCount(OthelloBoard.P1) +
					 " ○:" + othello.getCount(OthelloBoard.P2) + "    "
					 + player + " moves next");
		}
	}
}
