package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.*;

/**
 * Print Board String in console.
 * 
 * @author lamadri2
 *
 */
public class VPrintBoard implements Observer {

	public void update(Observable o) {
		Othello othello = (Othello)	o;
		if (othello.isGameOver()) {
			System.out.println("Game is over.");
			if (othello.getWinner() == ' ') {
				System.out.println("Game ended in a tie.");
			}
			else {
				System.out.println(othello.getWinner() + " wins.");
			}
		}
		System.out.println(othello.getBoardString());
		System.out.println(othello.getWhosTurn()+ "'s turn");
	}
	
}

