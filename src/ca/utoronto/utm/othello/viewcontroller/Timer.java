package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Keeps track of the time.
 * 
 * @author nellippi 
 *
 */
public class Timer {
	
	public Timeline timer;
	public KeyFrame keyframe;
	public CTimerEventHandler timertask;
	
	public Timer(Seconds p1sec, Seconds p2sec, Label p1time, Label p2time, Othello othello,
			VGameStatus vGameStatus, VBoard vBoard) {
		this.timertask = new CTimerEventHandler(p1sec, p2sec, p1time, p2time, othello, vGameStatus, vBoard);
		this.keyframe = new KeyFrame(Duration.seconds(1), this.timertask);
		this.timer = new Timeline(this.keyframe);
	}
	
	public void start() {
		this.timer.setCycleCount(Animation.INDEFINITE);
		this.timer.play();
	}
	
	public void stop() {
		this.timer.stop();
	}
	
}
