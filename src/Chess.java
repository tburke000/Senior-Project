import java.awt.*;
import java.util.LinkedList;

public class Chess extends Game {
    public LinkedList<Piece> pieces;
    public String name = "Chess";
    private int nPieces = 32;
    Board board = new Board("chessboard");

    @Override
    public void start() {
        display();
    }

    @Override
    public void display() {
        //Add background,buttons, pieces, and other images to frame

        board.display();

        for (Piece i: pieces) {
            PiecePanel dontAbbreviateThat = new PiecePanel(i);
            dontAbbreviateThat.setBackground(Color.getColor("blue"));
            board.gameWindow.add(dontAbbreviateThat);
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
