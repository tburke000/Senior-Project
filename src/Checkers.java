import java.util.LinkedList;

public class Checkers extends Game {

    LinkedList<Piece> pieces;
    private int nCheckers = 24;

    public Checkers () {
        this .pieces = new LinkedList<>();

        // Fill the list of pieces
        for (int i = 0; i < nCheckers; i++) {
            // The first twelve are Player One's, and red
            if (i < 12) {
                Piece piece = new Piece(true, "checker");
                pieces.add(piece);

            // The last twelve are Player Two's, and black
            } else {
                Piece piece = new Piece(false, "checker");
                pieces.add(piece);
            }
        }
    }
    @Override
    public void start() {

    }


    @Override
    public void display() {
        //Add background,buttons, pieces, and other images to frame
        gameWindow.setLayout(null);
        //gameWindow.setContentPane(insertBackground());
        //savebutton();
       // deletePicture();
     //   printDoodads();
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
