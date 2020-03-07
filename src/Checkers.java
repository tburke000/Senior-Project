import java.util.LinkedList;

public class Checkers extends Game {

    public LinkedList<Piece> pieces;
    public String name = "Checkers";
    private int nCheckers = 24;
    Board board = new Board("checkerboard");

    public Checkers () {
        this.pieces = new LinkedList<>();
        // Fill the list of pieces
        fillCheckers();
    }
    @Override
    public void start() {
        display();
    }

    private void fillCheckers () {
        int row = 0;
        int column = 0;

        // The first twelve are Player One's, and red
        for (int i = 0; i < nCheckers/2; i++) {
            Piece piece = new Piece(0, 0, true, "checker");
            piece.setPosition(row, column);
            if ((column <= 5) && ((row%2 == 0))) {
                column+=2;
            } else if ((row%2==0)) {
                row++;
                column=1;
            } else if ((column <=5) && (row%2!=0)) {
                column+=2;
            } else {
                row++;
                column = 0;
            }
            pieces.add(piece);
        }
        row = 7;
        column = 0;
        // The last twelve are Player Two's, and black
        for (int i = 12; i < nCheckers; i++) {
            Piece piece = new Piece(0, 0, false, "checker");
            piece.setPosition(row, column);
            if ((column <= 5) && ((row%2 != 0))) {
                column+=2;
            } else if ((row%2!=0)) {
                row--;
                column=1;
            } else if ((column <=5) && (row%2==0)) {
                column+=2;
            } else {
                row--;
                column = 0;
            }
            pieces.add(piece);
        }
    }

    @Override
    public void display() {
        //Add background,buttons, pieces, and other images to frame

        for (Piece i: pieces) {
            PiecePanel dontAbbreviateThat = new PiecePanel(i);

            board.gameWindow.add(dontAbbreviateThat);

        }
        //gameWindow.setContentPane(insertBackground());
        //savebutton();p
        // deletePicture();
        //   printDoodads();
        board.display();
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
