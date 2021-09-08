package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.*;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main othello application.
 * 
 * @author arnold, MAT137Strugglers
 *
 */
public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put 
	// --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	public static final String style = "stylep1.css";
	public void start(Stage stage) throws Exception {
		
		// Create and hook up the Model, View and the controller
		
		// MODEL
		Othello othello = new Othello();
		
		// CONTROLLER
		// CONTROLLER->MODEL hookup
		ArrayList<Othello> saveScenes = new ArrayList<Othello>();
		ArrayList<ArrayList<Integer>> saveTimer = new ArrayList<ArrayList<Integer>>();
		Seconds p1sec = new Seconds(300);
		Seconds p2sec = new Seconds(300);
		Seconds newp1sec = new Seconds(-1);
		Seconds newp2sec = new Seconds(-1);
		
		// VIEW
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5,5,5,5));
		grid.setStyle("-fx-background-color: #34ebc9");
		GridPane choosePlayerGrid = new GridPane();
		choosePlayerGrid.setPadding(new Insets(5,5,5,5));
		choosePlayerGrid.setStyle("-fx-background-color: #34ebc9");
		Label hintLabel = new Label("(row, col)");
		VBoard vBoard = new VBoard(saveScenes, saveTimer, p1sec, p2sec, hintLabel);
		vBoard.setVgap(5);
		vBoard.setHgap(5);
		VGameStatus vGameStatus = new VGameStatus();

		vGameStatus.setText("●:2" + " ○:2" + "    ● moves next");
		Label p1time = new Label("●: 5:00");
		Label p2time = new Label("○: 5:00");

		TextField time1 = new TextField();
		TextField time2 = new TextField();

		Timer timer = new Timer(p1sec, p2sec, p1time, p2time, othello, vGameStatus, vBoard);
		int index = 2;

		//Creates labels that show which type of players are playing
		VPlayerStatus vPlayer1Status = new VPlayerStatus();
		vPlayer1Status.setText(" ●: Human");
		VPlayerStatus vPlayer2Status = new VPlayerStatus();
		vPlayer2Status.setText("○: Human");
		
		// VPrintBoard vPrintBoard = new VPrintBoard();
		
		Label player1Label = new Label("Opponent:    ");
		ToggleGroup groupPlayer1 = new ToggleGroup(); 
	    RadioButton p1HumanRadio = new RadioButton("Human   "); 
	    p1HumanRadio.setToggleGroup(groupPlayer1); 
	    p1HumanRadio.setSelected(true);
	    RadioButton p1GreedyRadio = new RadioButton("Greedy   "); 
	    p1GreedyRadio.setToggleGroup(groupPlayer1); 
	    RadioButton p1RandomRadio = new RadioButton("Random   "); 
	    p1RandomRadio.setToggleGroup(groupPlayer1); 
	    RadioButton p1AdvancedRadio = new RadioButton("Advanced   "); 
	    p1AdvancedRadio.setToggleGroup(groupPlayer1); 
	    
	    //hint stuff
	    Button btnHint = new Button("Hint");
	    Label selectHintLabel = new Label("Hint Type:");
	    ToggleGroup hintsGroup = new ToggleGroup();
	    RadioButton randomHintRadio = new RadioButton("Random");
	    randomHintRadio.setSelected(true);
	    randomHintRadio.setToggleGroup(hintsGroup);
	    RadioButton greedyHintRadio = new RadioButton("Greedy");
	    greedyHintRadio.setToggleGroup(hintsGroup);
	    RadioButton advancedHintRadio = new RadioButton("Advanced");	    
	    advancedHintRadio.setToggleGroup(hintsGroup);
	    CheckBox permanentHintToggler = new CheckBox("Show Hint Permanently");
	    CHintEventHandler hintHandler = new CHintEventHandler(othello, hintLabel, vBoard);
	    btnHint.setOnAction(hintHandler);
	    CPermanentHintEventHandler permanentHintHandler = new CPermanentHintEventHandler(othello, hintLabel, vBoard);
	    permanentHintToggler.setOnAction(permanentHintHandler);
	    CHintTypeEventHandler hintTypeEventHandler = new CHintTypeEventHandler(othello);
	    randomHintRadio.setOnAction(hintTypeEventHandler);
	    greedyHintRadio.setOnAction(hintTypeEventHandler);
	    advancedHintRadio.setOnAction(hintTypeEventHandler);
	    
		// VIEW->CONTROLLER hookup
	    COpponentEventHandler opponentHandler = new COpponentEventHandler(groupPlayer1, vPlayer1Status, vPlayer2Status, 
				othello);
		p1HumanRadio.setOnAction(opponentHandler);
		p1RandomRadio.setOnAction(opponentHandler);
		p1GreedyRadio.setOnAction(opponentHandler);
		p1AdvancedRadio.setOnAction(opponentHandler);
		Button btnReset = new Button("RESET");
		Button btnUndo = new Button("UNDO");
		btnUndo.setOnAction(new CUndoEventHandler(saveScenes, othello, vBoard, vGameStatus, saveTimer, p1sec, p2sec,
				p1time, p2time, timer, index, hintHandler, hintTypeEventHandler, newp1sec, newp2sec, permanentHintHandler,
				hintLabel));
		btnReset.setOnAction(new CResetEventHandler(othello, vBoard, saveScenes, vGameStatus, timer, p1sec,
				p2sec, p1time, p2time, saveTimer, p1HumanRadio, opponentHandler, index, hintHandler,
				hintTypeEventHandler, newp1sec, newp2sec, permanentHintHandler, hintLabel));

		for(int row = 0; row <= 7; row ++) {
			for(int col = 0; col <= 7; col ++) {
				Button button = new Button();
				button.setFont(Font.font("Monospaced", 20));
				
				if (row==3&&col==3||row==4&&col==4){
					button.setStyle("-fx-background-color: #000000");
				} else if (row==3&&col==4||row==4&&col==3){
					button.setStyle("-fx-background-color: #ffffff");
				} 
				
				// Highlights buttons that can be moved to 				
				OthelloBoard copyOthello = othello.board.copy();
				if (copyOthello.move(row, col, othello.getWhosTurn())) {
					button.setStyle("-fx-inner-border: #0000FF");
					copyOthello = othello.board.copy();
				}
				
				double r=20;
				button.setShape(new Circle(r));
				button.setMinSize(2*r, 2*r);
				button.setMaxSize(2*r, 2*r);
				
			    vBoard.add(button, col, row, 1, 1);
			    button.setOnAction(new CMoveEventHandler(row, col, othello, saveScenes, saveTimer, p1sec, p2sec, hintLabel));
			}
		}
			
		grid.getStylesheets().add(style);
		
		grid.add(vBoard, 0, 0, 4, 1);
		grid.add(vGameStatus, 0, 1, 4, 1);
		grid.add(player1Label, 0, 2);
		grid.add(p1HumanRadio, 1, 2);
		grid.add(p1RandomRadio, 2, 2);
		grid.add(p1GreedyRadio, 3, 2);
		grid.add(p1AdvancedRadio, 1, 3);
		grid.add(btnReset, 2, 4);
		grid.add(vPlayer1Status, 2, 1);
		grid.add(vPlayer2Status, 3, 1);
		grid.add(btnUndo, 3, 4);
		
		grid.add(p1time, 0, 4);
		grid.add(p2time, 1, 4);
		
		grid.add(btnHint, 0, 5);
		grid.add(hintLabel, 1, 5);
		grid.add(selectHintLabel, 0, 6);
		grid.add(randomHintRadio, 1, 6);
		grid.add(greedyHintRadio, 2, 6);
		grid.add(advancedHintRadio, 3, 6);
		grid.add(permanentHintToggler, 2, 5, 2, 1);
		
		// Player selects black or white (Player 1 or player 2) and specifies time
	    
	    Rectangle rectangle = new Rectangle();  
	    rectangle.setX(0); 
	    rectangle.setY(0); 
	    rectangle.setWidth(300); 
	    rectangle.setHeight(50); 
	    rectangle.setFill(Color.web("#34ebc9"));
		Button btnBlack = new Button();
		Button btnWhite = new Button();
		btnBlack.setStyle("-fx-background-color: #000000");
		btnWhite.setStyle("-fx-background-color: #ffffff");
		Label lblChooseColour = new Label(" Click the colour you wish to play as! \n"
				+ "                (Black goes first)");
		lblChooseColour.setFont(new Font("Arial", 19));
		Label lblTypeTime = new Label(" Type in the time for each player! \n" + 
		"              (Format: seconds) \n"
				+ "       (If input is invalid or blank, it \n"
		 + "defaults to 5 minutes for that player)");
		lblTypeTime.setFont(new Font("Arial", 19));
		Label player1 = new Label("Player 1:");
		Label player2 = new Label("Player 2:");
		player1.setFont(new Font("Arial", 19));
		player2.setFont(new Font("Arial", 19));
		
		double r = 85;
		btnBlack.setShape(new Circle(r));
		btnBlack.setMinSize(2*r, 2*r);
		btnBlack.setMaxSize(2*r, 2*r);
		btnWhite.setShape(new Circle(r));
		btnWhite.setMinSize(2*r, 2*r);
		btnWhite.setMaxSize(2*r, 2*r);
		
		FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1500), btnBlack);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), btnWhite);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), lblChooseColour);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), time1);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), time2);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), player1);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), player2);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		fadeInTransition = new FadeTransition(Duration.millis(1500), lblTypeTime);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		fadeInTransition.play();
		
		choosePlayerGrid.add(rectangle, 0, 0, 2, 1);
		choosePlayerGrid.add(btnBlack, 0, 1);
		choosePlayerGrid.add(btnWhite, 1, 1);
		choosePlayerGrid.add(lblChooseColour, 0, 2, 2, 1);
		choosePlayerGrid.add(time1, 1, 3);
		choosePlayerGrid.add(time2, 1, 4);
		choosePlayerGrid.add(lblTypeTime, 0, 5, 2, 1);
		choosePlayerGrid.add(player1, 0, 3);
		choosePlayerGrid.add(player2, 0, 4);
		grid.add(choosePlayerGrid, 0, 0, 4, 7);
		
		btnBlack.setOnAction(new CChoosePlayerEventHandler("black", grid, choosePlayerGrid, vBoard, p1sec, p2sec, p1time,
				p2time, time1, time2, newp1sec, newp2sec, timer));
		btnWhite.setOnAction(new CChoosePlayerEventHandler("white", grid, choosePlayerGrid, vBoard, p1sec, p2sec, p1time,
				p2time, time1, time2, newp1sec, newp2sec, timer));
		
		// SCENE
		Scene scene = new Scene(grid); 
		stage.setTitle("Othello");
		stage.setScene(scene);
		// MODEL->VIEW hookup
		//othello.attach(vPrintBoard);
		othello.attach(vBoard);
		othello.attach(vGameStatus);
		
		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
