package ca.utoronto.utm.util;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;


/**
 * Class that represents the Greedy Strategy in the Othello game.
 * 
 * The GreedyStrategy chooses a move by considering all possible moves that the 
 * player can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 *
 * @author bhojakma
 */
public class GreedyStrategy implements PlayerStrategies{
	public GreedyStrategy() {
	}
	
	@Override
	public Move getMove(Othello othello, char player) {
		Othello othelloCopy = othello.copy();
		Move bestMove=new Move(0,0);
		int bestMoveCount=othelloCopy.getCount(player);;
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				othelloCopy = othello.copy();
				if(othelloCopy.move(row, col) && othelloCopy.getCount(player)>bestMoveCount) {
					bestMoveCount = othelloCopy.getCount(player);
					bestMove = new Move(row,col);
				}
			}
		}
		return bestMove;
	}

}

