package ca.utoronto.utm.util;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;


/**
 * Interface that represents the strategies that players 
 * use to make moves in the Othello game.
 * 
 * @author bhojakma
 */
public interface PlayerStrategies {
	public Move getMove(Othello othello, char player);
}
