package ca.utoronto.utm.othello.model;
/**
 * A Move is where a disk is placed when a Player makes a valid move
 * on the Othello Board.
 *
 * @author kamathya
 *
 */
public class Move {
	private int row,col;
	
	/**
	 * Creates a new Move that is valid on the Othello Board for
	 * a player at position row, col.
	 * 
	 * @param row
	 * @param col
	 */
	
	public Move(int row, int col) {
		this.row=row; this.col=col;
	}
	
	/**
	 * Returns the row of the move.
	 * 
	 * @return int This returns the row of the Move
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of the move.
	 * 
	 * @return int This returns the column of the Move
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Returns the string representation of the row and column.
	 * 
	 * @return String Returns the row and column
	 */
	public String toString() {
		return "("+this.row+","+this.col+")";
	}
}
