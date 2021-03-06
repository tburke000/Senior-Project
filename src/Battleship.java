import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class representing the game Battleship playable using the Knight's Party Table
 *
 * @author burke
 */
public class Battleship extends Game {
    private LinkedList<Piece> pieces;
    private LinkedList<String> ships;
    private int nShips = 10;
    private int nRedPegs = 26;
    private int nWhitePegs = 50;
    Board board = new Board("battleship");

    public Battleship () {
        this.pieces = new LinkedList<>();
        this.ships = new LinkedList<>(Arrays.asList("carrier", "battleship", "cruiser", "submarine", "destroyer"));
        fillShips();
    }

    @Override
    public void start() {
        display();
    }

    /**
     * Displays all components for Battleship
     */
    @Override
    public void display() {
        board.display();
        for (Piece i: pieces) {
            PiecePanel visual = new PiecePanel(i);
            if (i.getOwner()) {
                    board.player1Board.add(visual);

                } else {
                    board.player2Board.add(visual);
                }

        }
    }

    /**
     * Fills the list of ships for Battleship
     */
    private void fillShips () {
        for (String i : ships) {
            Piece piece = new Piece(0, 0, true, i);
            pieces.add(piece);
        }

        for (String i : ships) {
            Piece piece = new Piece(0, 0, false, i);
            pieces.add(piece);
        }
    }

    @Override
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        return true;
    }

    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }
}
