package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.HumanStrategy;

/**
 * PlayerHuman makes a move by selecting a valid position on the Othello
 * Board. Each move leaves the player with a total number of tokens.
 * 
 * @author kamathya
 *
 */
public class PlayerHuman extends Player {

	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
		this.strategy = new HumanStrategy();
	}

	public Move getMove() {
		return this.strategy.getMove(this.othello, this.player);
	}

}


