import java.util.Arrays;
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

    @Override
    public void display() {
        board.display();
        for (Piece i: pieces) {
            PiecePanel visual = new PiecePanel(i);
            board.gameWindow.add(visual);
        }
    }

    private void fillShips () {
        for (String i : ships) {
            Piece piece = new Piece(0, 0, true, i);
            pieces.add(piece);
        }

        for (String i : ships) {
            Piece piece = new Piece(0, 0, false, i);
            pieces.add(piece);
        }

        for (int i = 0; i < nRedPegs; i++) {
            if (i < nRedPegs/2) {
                Piece peg = new Piece(0, 0, true, "redpeg");
                pieces.add(peg);
            } else {
                Piece peg = new Piece(0, 0, false, "redpeg");
                pieces.add(peg);
            }
        }

        for (int i = 0; i < nWhitePegs; i++) {
            if (i < nWhitePegs/2) {
                Piece peg = new Piece(0, 0, true, "whitepeg");
                pieces.add(peg);
            } else {
                Piece peg = new Piece(0, 0, false, "whitepeg");
                pieces.add(peg);
            }
        }
    }

    @Override
    public void movePiece(Piece piece) {

    }

    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }
}
