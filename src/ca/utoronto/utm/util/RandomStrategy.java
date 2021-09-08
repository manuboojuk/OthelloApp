package ca.utoronto.utm.util;

import java.util.ArrayList;


import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import java.util.Random;


/**
 * Class that represents the Random Strategy in the Othello game.
 * 
 * The Random strategy chooses a move by picking a random move from
 * all possible choices.
 * 
 * @author bhojakma
 */
public class RandomStrategy implements PlayerStrategies{
	private Random rand = new Random();
	public RandomStrategy() {
	}
	
	@Override
	public Move getMove(Othello othello, char player) {
		ArrayList<Move> moves = othello.board.getAvailableMoves(player);
		if (moves.size() > 0) {
			return moves.get(this.rand.nextInt(moves.size()));
		}
		else {
			return null;
		}
	}
}
