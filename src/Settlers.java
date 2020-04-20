import java.util.Set;

/**
 * Class representing the board game Settlers of Catan, playable using the Knight's Party Table.
 */
public class Settlers extends Game {

    public Settlers () {

    }

    @Override
    public void start() {
        display();
    }

    @Override
    public void display() {

    }

    @Override
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        return false;
    }

    /**
     * Not necessary for Settlers of Catan
     * @param taker The piece being moved to take the takee
     * @param takee The piece being taken
     */
    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }
}
