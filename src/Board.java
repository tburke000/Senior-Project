import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            this.boardDisplay = createImageIcon(type+".png", type);
            System.out.println(boardDisplay.getDescription());
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
                    pauseMenu.setLocation(440, 240);
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
                            launcher.setSize(1280, 1024);
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
        if (type.equals("battleship")) {
            battleshipDisp();
        } else if (type.equals("settlers")) {
            settlersDisp();
        } else {
            disp.setSize(1280, 1024);
            disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
            disp.setUndecorated(true);

            gameWindow.setPreferredSize(new Dimension(1280, 1024));
            gameWindow.setBorder(new EmptyBorder(0, 128, 0, 128));

            disp.add(gameWindow);
            disp.setVisible(true);
        }
    }

    private void battleshipDisp () {
        disp.setSize(1280, 3072);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        disp.setUndecorated(true);
        BoardPanel panel2 = new BoardPanel(createImageIcon("battleship2.png", "Board"));
        panel2.setPreferredSize(new Dimension(1080,1080));
        panel2.setBorder(new EmptyBorder(1080,100,0,100));
        gameWindow.setPreferredSize(new Dimension(1080, 1080));
        gameWindow.setBorder(new EmptyBorder(0, 100, 0, 100));

        disp.add(gameWindow);
        disp.setVisible(true);
    }

    private void settlersDisp () {
        disp.setSize(1280, 1024);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        disp.setUndecorated(true);

        BoardPanel player1View = new BoardPanel(boardDisplay);
        BoardPanel player2View = new BoardPanel(boardDisplay);

        // Holds buttons for generating roads, settlements, and resources
        JPanel thyHolyButtonHolder1 = new JPanel();
        JPanel thyHolyButtonHolder2 = new JPanel();
        JButton roadButton = new JButton("Roads");
        JButton settlementButton = new JButton("Settlements");
        JButton cityButton = new JButton("Cities");

        roadButton.addActionListener(bigActionListener("roads"));
        settlementButton.addActionListener(bigActionListener("settlements"));
        cityButton.addActionListener(bigActionListener("cities"));

        thyHolyButtonHolder1.add(roadButton);
        thyHolyButtonHolder1.add(settlementButton);
        thyHolyButtonHolder1.add(cityButton);

        // Establish three views, one for each player and one for the main display
        player1View.setPreferredSize(new Dimension(1280, 1024));
        player1View.setBorder(new EmptyBorder(0, 100, 0, 100));
        player2View.setPreferredSize(new Dimension(1280, 1024));
        player2View.setBorder(new EmptyBorder(0, 100, 0, 100));
        gameWindow.setPreferredSize(new Dimension(1280, 1024));

//        disp.add(player1View);
//        disp.add(thyHolyButtonHolder1);
        disp.add(gameWindow);
//        disp.add(thyHolyButtonHolder2);
//        disp.add(player2View);

        disp.setVisible(true);
    }

    /**
     * Creates an action listener for Thy Holy Button Holder
     * @return An action listener for Thy Holy Button Holder
     */
    private ActionListener bigActionListener (String thingusPlaceHolderTitle) {
        ActionListener bigAl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (thingusPlaceHolderTitle) {
                    case "Roads":
                        PiecePanel road = new PiecePanel(new Piece(0,0, false, "roads"));
                        gameWindow.add(road);
                        break;
                    case "Settlements":
                        PiecePanel settlements = new PiecePanel(new Piece(0,0, false, "settlements"));
                        gameWindow.add(settlements);
                        break;
                    case "Cities":
                        PiecePanel cities = new PiecePanel(new Piece(0,0, false, "cities"));
                        gameWindow.add(cities);
                        break;
                    default:
                }
            }
        };
        return bigAl;
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


        if (board != null) {
            if (board.getDescription().contains("settlers")) {
                graphic.clearRect(0, 0, 1080, 1080);
                graphic.drawImage(board.getImage(), 0, 0, 1280, 1024, this);
            } else {
                graphic.clearRect(0, 0, 1024, 1024);
                graphic.drawImage(board.getImage(), 0, 0, 1280, 1024, this);
            }
        }
    }
}