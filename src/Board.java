import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Class representing a game board.
 */
public class Board {
    public Square[][] board;
    public ImageIcon boardDisplay;

    public Board () {
        this.board = new Square[8][8];

        Square square;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                square = new Square(row, column);
                board[row][column] = square;
            }
        }
    }

    public void display() {
        try {
            this.boardDisplay = new ImageIcon("checkerboard.jpg");
        } catch (Exception e) {
            System.out.print("Error finding image");
        }
        JFrame jf = new JFrame();
        jf.setSize(1920, 1080);
        JPanel panel = new JPanel();



        JLabel background = new JLabel(boardDisplay, JLabel.CENTER);
        //jf.pack();
        jf.add(background);

        jf.setVisible(true);


    }

    public static void main(String[] args) {
        Board board = new Board();
        board.display();
    }
}