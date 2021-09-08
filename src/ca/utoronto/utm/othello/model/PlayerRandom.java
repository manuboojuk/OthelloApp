package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.RandomStrategy;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author arnold
 *
 */
public class PlayerRandom extends Player {
	public PlayerRandom(Othello othello, char player) {
		super(othello, player);
		this.strategy = new RandomStrategy();
		
	}
	
	@Override
	public Move getMove() {
		return this.strategy.getMove(this.othello, this.player);
	}
}

