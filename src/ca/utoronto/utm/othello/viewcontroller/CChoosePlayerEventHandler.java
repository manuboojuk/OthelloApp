package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Determines the user's token color and desired time for both players in seconds,
 * depending on the user's input.
 * 
 * @author lamadri2
 *
 */

public class CChoosePlayerEventHandler implements EventHandler<ActionEvent>{

	private String colour;
	private GridPane grid;
	private GridPane choosePlayerGrid;
	private VBoard vBoard;
	private Seconds p1sec;
	private Seconds p2sec;
	private Label p1time;
	private Label p2time;
	private TextField time1;
	private TextField time2;
	private Seconds newp1sec;
	private Seconds newp2sec;
	private Timer timer;
	
	public CChoosePlayerEventHandler(String colour, GridPane grid, GridPane choosePlayerGrid, VBoard vBoard, 
			Seconds p1sec, Seconds p2sec, Label p1time, Label p2time, TextField time1, TextField time2,
			Seconds newp1sec, Seconds newp2sec, Timer timer) {
		this.colour = colour;
		this.grid = grid;
		this.vBoard = vBoard;
		this.p1sec = p1sec;
		this.p2sec = p2sec;
		this.time1 = time1;
		this.time2 = time2;
		this.choosePlayerGrid = choosePlayerGrid;
		this.p1time = p1time;
		this.p2time = p2time;
		this.newp1sec = newp1sec;
		this.newp2sec = newp2sec;
		this.timer = timer;
	}
	
	public void handle(ActionEvent event) {
		PlayerColour.getInstance().colour =  this.colour;
		grid.getChildren().remove(choosePlayerGrid);
        try {
            newp1sec.sec = Integer.parseInt(time1.getText());
            if (newp1sec.sec >= 0) {
    			p1sec.sec = newp1sec.sec;
    			this.p1time.setText(p1sec.toString(OthelloBoard.P1));
            }
            else {
            	p1sec.sec = 300;
            }
        }
        catch(NumberFormatException nfe) {
        	p1sec.sec = 300;
        }
        try {
    		newp2sec.sec = Integer.parseInt(time2.getText());
    		if (newp2sec.sec >= 0) {
        		p2sec.sec = newp2sec.sec;
        		this.p2time.setText(p2sec.toString(OthelloBoard.P2));
    		}
    		else {
    			p2sec.sec = 300;
    		}
        }
        catch(NumberFormatException nfe) {
        	p2sec.sec = 300;
        }
        timer.start();
		
		//Fade in transition
		FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), vBoard);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		
	}
}
