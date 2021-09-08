package ca.utoronto.utm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;


/**
 * Class that represents the Human Strategy in the Othello game.
 * 
 * The HumanStrategy chooses a move by consulting a human and 
 * then making the move that the human chooses.
 * 
 * @author bhojakma
 */

public class HumanStrategy implements PlayerStrategies{
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));
	
	public HumanStrategy() {
	}
	
	@Override
	public Move getMove(Othello othello, char player) {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	private int getMove(String message) {
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = HumanStrategy.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}

