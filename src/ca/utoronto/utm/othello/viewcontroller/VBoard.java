package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.*;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * View Class for GUI representation of board.
 * 
 * @author lamadri2
 *
 */
public class VBoard extends GridPane implements Observer {
	
	private ArrayList<Othello> saveScenes;
	public static final String stylep1 = "stylep1.css";
	public static final String stylep2 = "stylep2.css";
	private ArrayList<ArrayList<Integer>> saveTimer;
	private Seconds p1sec;
	private Seconds p2sec;
	private Label hintLabel;
	
	public VBoard(ArrayList<Othello> saveScenes, ArrayList<ArrayList<Integer>> saveTimer, Seconds p1sec, Seconds p2sec, Label hintLabel) {
		this.saveScenes = saveScenes;
		this.saveTimer = saveTimer;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.hintLabel = hintLabel;
	}
	
	public void update(Observable o) {
		Othello othello = (Othello)	o;
		if (othello.getWhosTurn() == OthelloBoard.P1) {
			this.getStylesheets().remove(stylep2);
			this.getStylesheets().add(stylep1);
		} else {
			this.getStylesheets().remove(stylep1);
			this.getStylesheets().add(stylep2);
		}
		FadeTransition fadeOut = new FadeTransition(Duration.millis(250), this);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		
		FadeTransition fadeIn = new FadeTransition(Duration.millis(250), this);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeOut.setOnFinished(new FadeInEventHandler(fadeIn, this, othello, saveScenes, saveTimer, p1sec, p2sec, hintLabel));
		
	}
}
