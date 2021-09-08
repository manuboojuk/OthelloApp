package ca.utoronto.utm.util;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;

/**
 * Class that represents the Advanced Strategy in the Othello game.
 * 
 * Choose a corner if available, otherwise choose a side if available, 
 * otherwise choose a space that maximizes the players presence in the 
 * middle 4 by 4 square of the board, otherwise choose the location 
 * maximizing the movers presence on the board.
 *
 * @author bhojakma
 */
public class AdvancedStrategy implements PlayerStrategies{
	public AdvancedStrategy() {
	}

	@Override
	public Move getMove(Othello othello, char player) {
		ArrayList<Move> moves = othello.board.getAvailableMoves(player);
		int outerIndex = Othello.DIMENSION - 1;
		
		//check all available moves for a corner move
		for (int i = 0; i < moves.size(); i++) {
			
			//top left move
			if (moves.get(i).getRow() == 0 && moves.get(i).getCol() == 0) {
				return moves.get(i);
			} 
			
			//top right move
			else if (moves.get(i).getRow() == 0 && moves.get(i).getCol() == outerIndex) {
				return moves.get(i);
			}
			
			//bottom left move
			else if (moves.get(i).getRow() == outerIndex && moves.get(i).getCol() == 0) {
				return moves.get(i);
			}
			
			//bottom right move
			else if (moves.get(i).getRow() == outerIndex && moves.get(i).getCol() == outerIndex) {
				return moves.get(i);
			}	
		}
		
		//check all available moves for an edge move
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i).getRow() == 0 || moves.get(i).getCol() == 0 || moves.get(i).getRow() ==  outerIndex || moves.get(i).getCol() == outerIndex) {
				return moves.get(i);
			}
		}
		
		
		//check all available moves for a middle move 
		//if there exists a move that increases presence in middle
		if (moveIncreasingPresenceInMiddle(othello, player, moves).size() == 1) { 
			return moveIncreasingPresenceInMiddle(othello, player, moves).get(0);
		}
		
		//check all available moves for a greedy move
		else {
			GreedyStrategy greedyStrat = new GreedyStrategy();
			return greedyStrat.getMove(othello, player);
		}
	}
	
	/**
	 * Private helper method for getMove.
	 * 
	 * @param othello The current game of othello.
	 * @param player The advanced player whos Move we are trying to get.
	 * @param availableMoves An arraylist with all available moves of 
	 * @return An arraylist with either 0 or 1 element. If there are 
	 * 		   no moves that increase presence in middle, then  
	 * 		   return an empty arraylist If there is a move that 
	 * 		   increases presence in middle, return the move that 
	 * 		   increases presence in middle the most.
	 * 
	 * @author bhojakma
	 */
	private ArrayList<Move> moveIncreasingPresenceInMiddle(Othello othello, char player, ArrayList<Move> availableMoves) {
		
		int currentPresenceInMiddle = othello.board.presenceInMiddle(player);
		ArrayList<Move> bestMove = new ArrayList<Move>();
		
		for (int i = 0; i < availableMoves.size(); i++) {
			Othello othelloCopy = othello.copy();
			othelloCopy.move(availableMoves.get(i).getRow(), availableMoves.get(i).getCol());
			
			//If theres a move that increases presence in middle
			if (othelloCopy.board.presenceInMiddle(player) > currentPresenceInMiddle) {
				currentPresenceInMiddle = othelloCopy.board.presenceInMiddle(player);
				bestMove.add(0, availableMoves.get(i));
			}
		}
		return bestMove;
	}
}
