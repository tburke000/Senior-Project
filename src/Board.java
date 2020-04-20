import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Flow;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Class representing a game board programmatically
 * @author burke
 */
public class Board {
    // 2D Square array representing the board in a back end regard
    public Square[][] board;
    // ImageIcon representing the graphic of the board
    public ImageIcon boardDisplay;
    // String used to identify the type of board (to base the color of the squares off of)
    private String type;
    // BoardPanel (detailed below) representing the GUI of the board
    public BoardPanel gameWindow;
    // JFrame to store the board and display it
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

        // Listens if escape is pressed during a game, if so opens the pause menu
        gameWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    JFrame pauseMenu = new JFrame();
                    pauseMenu.setLayout(new GridBagLayout());
                    pauseMenu.setSize(400, 600);
                    pauseMenu.setLocation(760, 240);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.ipady = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    JLabel logo = new JLabel("", new ImageIcon("logo.png"), SwingConstants.CENTER);
                    JLabel label = new JLabel("PAUSE", SwingConstants.CENTER);
                    JButton returnMenu = new JButton("Return to Launcher");
                    returnMenu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            disp.dispose();
                            pauseMenu.dispose();

                            JFrame launcher = Launcher.dispMenu();
                            launcher.setSize(400, 600);
                            launcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            launcher.setSize(1920,1080);
                            launcher.setUndecorated(true);
                            launcher.setVisible(true);
                        }
                    });
                    pauseMenu.add(logo, gbc);
                    pauseMenu.add(label, gbc);
                    pauseMenu.add(returnMenu, gbc);
                    pauseMenu.setFocusable(true);
                    pauseMenu.setUndecorated(true);
                    pauseMenu.setVisible(true);
                    pauseMenu.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {}

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                pauseMenu.dispose();
                            }
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {                        }
                    });
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        gameWindow.setFocusable(true);
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

    /**
     * Displays the board
     */
    public void display() {
        disp.setSize(1920, 1080);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        disp.setUndecorated(true);

        gameWindow.setPreferredSize(new Dimension(1080, 1080));
        gameWindow.setBorder(new EmptyBorder(0, 420, 0, 420));

        disp.add(gameWindow);
        disp.setVisible(true);

    }

}

/**
 * Class representing a game board graphically
 * @author burke
 */
class BoardPanel extends JPanel {
    private ImageIcon board;
    public BoardPanel (ImageIcon board) {
        this.board = board;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.clearRect(420, 0, 1080, 1080);
        if (board != null) {
            graphic.drawImage(board.getImage(), 420, 0, 1080, 1080, this);
        }
    }


}