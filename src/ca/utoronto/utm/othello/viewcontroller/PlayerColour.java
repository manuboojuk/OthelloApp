package ca.utoronto.utm.othello.viewcontroller;

/**
 * Keeps track of the current player color.
 * 
 * @author lamadri2
 *
 */
public class PlayerColour {
	String colour = "black";
	
	private PlayerColour() {}

    private static class PlayerColourHolder {
        private static final PlayerColour INSTANCE = new PlayerColour();
    }

    public static PlayerColour getInstance() {
        return PlayerColourHolder.INSTANCE;
    }
}
