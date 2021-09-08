package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.PlayerStrategies;

/**
 * Construct a new Player that can either be a Human Player, Greedy Player or Random Player.
 * @param othello
 * @param player P1 or P2
 */
public abstract class Player {
	
	protected Othello othello;
	protected char player;
	protected PlayerStrategies strategy;

	public Player(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}
	public char getPlayer() {
		return this.player;
	}
	public abstract Move getMove();
}
