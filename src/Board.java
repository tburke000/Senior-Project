import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Class representing a game board.
 */
public class Board {
    public Square[][] board;
    public ImageIcon boardDisplay;
    private String type;
    public BoardPanel gameWindow;
    public JFrame disp;
    public Board (String type) {
        this.board = new Square[8][8];
        this.type = type;
        Square square;

        // Find the board image
        try {
            this.boardDisplay = createImageIcon(type+".png", "Board");
        } catch (Exception e) {
            System.out.print("Error finding image");
        }

        this.disp = new JFrame();
        this.gameWindow = new BoardPanel(boardDisplay);

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
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    public void display() {

        disp.setLayout(new BoxLayout(disp.getContentPane(), Y_AXIS));
        disp.setSize(1920, 1080);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        disp.setUndecorated(true);

        gameWindow.setLayout(new GridLayout(8,8));
        //gameWindow.setPreferredSize(new Dimension(1080, 1080));
        gameWindow.setBorder(new EmptyBorder(0, 420, 0, 420));
        //gameWindow.setBackground(new Color(255, 255, 255, 0));



        disp.add(gameWindow);
        disp.setVisible(true);

    }

}

class BoardPanel extends JPanel {
    private ImageIcon board;
    public BoardPanel (ImageIcon board) {
        this.board = board;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.clearRect(0, 0, 80, 80);
        if (board != null) {
            graphic.drawImage(board.getImage(), 420, 0, 1080, 1080, this);
        }
    }


}