import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a game of Checkers playable using the Knight's Party Table
 * @author burke
 */
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

    /**
     * Loops through and generates checkers, then adds them to the board.
     */
    private void fillCheckers () {
        int row = 0;
        int column = 0;
        int xPos;
        int yPos;

        // The first twelve are Player One's, and red
        for (int i = 0; i < nCheckers/2; i++) {
            xPos = 420 + (column*80);
            yPos = row * 80;
            Piece piece = new Piece(xPos, yPos, true, "checker");
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
            xPos = 420 + (column*80);
            yPos = row * 80;
            Piece piece = new Piece(xPos, yPos, false, "checker");
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
        board.display();

        for (Piece i: pieces) {
            PiecePanel visual = new PiecePanel(i);
            visual.setLocation(new Point(i.getxAxis(), i.getyAxis()));

            board.gameWindow.add(visual);
            System.out.println(visual.getLocation());
        }
    }

    @Override
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        return false;
    }

    /**
     * Method responsible for generating a list of legal moves for a given piece, and returns whether or not the move being
     * made is of that list.
     * @param piece The piece being moved
     * @param xPos The x position the piece is being moved to
     * @param yPos The y position the piece is being moved to
     * @return True if the move is legal, false if not
     */
    public static boolean move(Piece piece, int xPos, int yPos) {

        Square position = piece.getPosition();
        LinkedList<Square> possibleMoves = new LinkedList<>();

        if ((position.row++ <= 7) && (position.column++ <= 7)) {
            possibleMoves.add(new Square(position.row++,position.column++));
        } else if ((position.row-- >= 0) && (position.column-- >= 0)) {
            possibleMoves.add(new Square(position.row--,position.column--));
        } else if ((position.row -- >= 0) && (position.column++ <= 7)) {
            possibleMoves.add(new Square(position.row++,position.column++));
        } else if ((position.row ++ <= 7) && (position.column-- >= 0)) {
            possibleMoves.add(new Square(position.row++,position.column--));
        }

        return checkMoves(possibleMoves, xPos, yPos);
    }

    @Override
    public void takePiece(Piece taker, Piece takee) {
        Square position = taker.getPosition();
        Square oPosition = takee.getPosition();

        if ((position.row++)  == (oPosition.row) && (position.column++) == (oPosition.column)) {

        } else if ((position.row--)  == (oPosition.row) && (position.column--) == (oPosition.column)) {

        } else if ((position.row--)  == (oPosition.row) && (position.column++) == (oPosition.column)) {

        } else if ((position.row++)  == (oPosition.row) && (position.column--) == (oPosition.column)) {

        }
    }

    @Override
    public void win(Boolean player) {
        boolean didntWin = false;
        for (Piece i : pieces) {
            if (i.getOwner() != player) {
                didntWin = true;
            }
        }
    }
}
