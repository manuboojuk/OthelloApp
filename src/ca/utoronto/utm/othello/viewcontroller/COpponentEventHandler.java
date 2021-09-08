package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerAdvanced;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Changes opponent type mid-game (Human, Greedy, Random, Advanced)
 * 
 * @author lamadri2
 *
 */

public class COpponentEventHandler implements EventHandler<ActionEvent> {
	private ToggleGroup group;
	private VPlayerStatus player1Status;
	private VPlayerStatus player2Status;
	public Othello othello;
	private PlayerGreedy greedy;
	private PlayerRandom random;
	private PlayerAdvanced advanced;
	
	public COpponentEventHandler(ToggleGroup group, VPlayerStatus player1Status, VPlayerStatus player2Status, 
			Othello othello) {
		this.group = group;
		this.player1Status = player1Status;
		this.player2Status = player2Status;
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		if (PlayerColour.getInstance().colour == "black") {
			this.greedy = new PlayerGreedy(othello, 'O');
			this.random = new PlayerRandom(othello, 'O');
			this.advanced = new PlayerAdvanced(othello, 'O');
		}
		else {
			this.greedy = new PlayerGreedy(othello, 'X');
			this.random = new PlayerRandom(othello, 'X');
			this.advanced = new PlayerAdvanced(othello, 'X');
		}
		RadioButton opponentRadio = (RadioButton) group.getSelectedToggle();
		Opponent.getInstance().opponent = opponentRadio.getText();
		if (PlayerColour.getInstance().colour == "black") {
			player2Status.update(opponentRadio.getText());
		}
		else {
			player1Status.update(opponentRadio.getText());
		}
		if (Opponent.getInstance().opponent.startsWith("Greedy")) {
			Move move = greedy.getMove();
			if (othello.getWhosTurn() == 'X' && PlayerColour.getInstance().colour == "white") {
				
				othello.move(move.getRow(), move.getCol());
			}
			else if (othello.getWhosTurn() == 'O' && PlayerColour.getInstance().colour == "black") {
				
				othello.move(move.getRow(), move.getCol());
			}
		}
		else if (Opponent.getInstance().opponent.startsWith("Random")) {
			Move move = random.getMove();
			if (othello.getWhosTurn() == 'X' && PlayerColour.getInstance().colour == "white") {
				othello.move(move.getRow(), move.getCol());
			}
			else if (othello.getWhosTurn() == 'O' && PlayerColour.getInstance().colour == "black") {
				othello.move(move.getRow(), move.getCol());
			}
		}
		else if (Opponent.getInstance().opponent.startsWith("Advanced")) {
			Move move = advanced.getMove();
			if (othello.getWhosTurn() == 'X' && PlayerColour.getInstance().colour == "white") {
				othello.move(move.getRow(), move.getCol());
			}
			else if (othello.getWhosTurn() == 'O' && PlayerColour.getInstance().colour == "black") {
				othello.move(move.getRow(), move.getCol());
			}
		}
		
	}
}