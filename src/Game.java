import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Abstract class representing a game on the Knight's Party Table
 */
public abstract class Game {
    public static JFrame gameWindow = new JFrame();
    public LinkedList<Piece> pieces = new LinkedList<>();
    public String name;
    /**
     * Starts the game.
     */
    public abstract void start();

    /**
     * Displays the game's board and pieces at the start
     */
    public abstract void display();

    /**
     * Moves the given piece
     * @param piece The given piece
     */
    public abstract void movePiece(Piece piece);

    /**
     *  Removes the takee piece from the board and replaces it with the taker
     * @param taker The piece being moved to take the takee
     * @param takee The piece being taken
     */
    public abstract void takePiece(Piece taker, Piece takee);

    /**
     * When the games win condition is met, this method ends the game
     * @param player The winner. True for Player One, False for Player Two
     */
    public abstract void win (Boolean player);

    //Get image file for game board background and convert it to a JLabel
    /*public static JLabel insertBackground() throws IOException {

    }*/
}

