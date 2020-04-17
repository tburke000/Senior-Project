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
    public abstract boolean movePiece(Piece piece, int xPos, int yPos);

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

    /**
     * Given a list of possible moves and an x and y position, returns whether or not
     * it's a viable move.
     * @return true if the move in question is viable, false if not
     * @author burke
     */
    public static boolean checkMoves (LinkedList<Square> possibleMoves, int xPos, int yPos) {
        for (Square i: possibleMoves) {
            // This checks to see if the piece is in the horizontal and vertical bounds of a square in an immensely convoluted manner.
            if ((xPos >= i.getPixel()[0] && xPos <= i.getPixel()[0]+80) && (yPos >= i.getPixel()[1] && yPos <= i.getPixel()[1]+80)) {
                return true;
            }
        }
        return false;
    }
}

