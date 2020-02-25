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
    private String type;
    public JFrame jf;
    public Board (String type) {
        this.board = new Square[8][8];
        this.type = type;
        this.jf = new JFrame();
        Square square;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                square = new Square(row, column);
                board[row][column] = square;
            }
        }
    }
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    public void display() {
        try {
            this.boardDisplay = createImageIcon(type+".png", "Board");
        } catch (Exception e) {
            System.out.print("Error finding image");
        }

        jf.setSize(1920, 1080);
        JPanel panel = new JPanel();



        JLabel background = new JLabel(boardDisplay, JLabel.CENTER);
        //jf.pack();
        jf.add(background);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jf.setUndecorated(true);
        jf.setVisible(true);


    }

    public static void main(String[] args) {
        Checkers ck = new Checkers();
        for (Piece i: ck.pieces) {
            System.out.println(i.pos.toString());
        }
    }
}