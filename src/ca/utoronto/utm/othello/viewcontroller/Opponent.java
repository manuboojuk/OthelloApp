package ca.utoronto.utm.othello.viewcontroller;

/**
 * Keeps track of the current opponent.
 * 
 * @author lamadri2
 *
 */
public class Opponent {
	String opponent = "Human";
	
	private Opponent() {}

    private static class OpponentHolder {
        private static final Opponent INSTANCE = new Opponent();
    }

    public static Opponent getInstance() {
        return OpponentHolder.INSTANCE;
    }
}

