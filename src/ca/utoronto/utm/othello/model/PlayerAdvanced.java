package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.AdvancedStrategy;

/**
 * PlayerAdvanced makes a move using an order of prioritization
 * AdvancedPlayer prioritizes corner moves first, then edge 
 * moves, then moves that increase the player's presence in 
 * the middle of the board most, then moves that increases the 
 * player's presence on the board the most.
 *
 * @author bhojakma
 * 
 */

public class PlayerAdvanced extends Player{
	public PlayerAdvanced(Othello othello, char player) {
		super(othello, player);
		this.strategy = new AdvancedStrategy();
	}
	
	@Override
	public Move getMove() {
		return this.strategy.getMove(this.othello, this.player);
	}

}
