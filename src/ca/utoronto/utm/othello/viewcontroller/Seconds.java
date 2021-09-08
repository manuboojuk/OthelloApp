package ca.utoronto.utm.othello.viewcontroller;

/**
 * Keeps track of player's seconds. Returns string representation of time.
 * 
 * @author nellippi 
 *
 */
public class Seconds {	
	public int sec;
	public Seconds(int sec) {
		this.sec = sec;
	}
	
	public String toString(char player) {
		String strPlayer;
		if (player == 'X') {
			strPlayer = "●";
		}
		else {
			strPlayer = "○";
		}
		if (sec % 60 < 10) {
			return strPlayer + ": " + Math.floorDiv(sec, 60) + ":0" + (sec % 60);
		}
		return strPlayer + ": " + Math.floorDiv(sec, 60) + ":" + (sec % 60);
	}
	
}

