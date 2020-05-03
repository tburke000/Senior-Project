import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


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
    // JFrame and BoardPanels for Player Views 1 and 2
    public JFrame player1View;
    public JFrame player2View;
    public BoardPanel player1Board;
    public BoardPanel player2Board;

    //These two are for Settlers, they hold player cards.
    public JPanel player1Tray;
    public JPanel player2Tray;
    // JFrame to store the board and display it
    public JFrame disp;

    public Board (String type) {
        this.board = new Square[8][8];
        this.type = type;
        Square square;

        // Find the board image
        try {
            this.boardDisplay = createImageIcon(type+".png", type);
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
        gameWindow.addKeyListener(pauseMenuListener());
        gameWindow.setFocusable(true);
    }

    private KeyListener pauseMenuListener () {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    JFrame pauseMenu = new JFrame();
                    pauseMenu.setLayout(new BorderLayout());
                    pauseMenu.setSize(400, 600);
                    pauseMenu.setLocation(440, 240);

                    JLabel label = new JLabel("PAUSE", SwingConstants.CENTER);
                    label.setBorder(new EmptyBorder(100, 0, 50, 0));
                    JPanel buttonPanel = new JPanel();
                    JPanel pausePanel = new JPanel(new BorderLayout());
                    JButton returnMenu = new JButton("Return to Launcher");

                    try {
                        BufferedImage image = ImageIO.read(new File("src/logoPause.png"));
                        JLabel logo  = new JLabel(new ImageIcon(image));
                        logo.setSize(new Dimension(400, 304));
                        pauseMenu.add(logo, BorderLayout.PAGE_START);

                    } catch (Exception uhOh) {
                        System.out.println("Looks like the pause menu logo should be here... but it isn't");
                    }

                    returnMenu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            disp.dispose();
                            if (player1View != null && player2View != null) {
                                player1View.dispose();
                                player2View.dispose();
                            }
                            pauseMenu.dispose();
                            JFrame launcher = Launcher.dispMenu();
                            launcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            launcher.setSize(1280, 1024);
                            launcher.setUndecorated(true);
                            launcher.setVisible(true);
                        }
                    });

                    pausePanel.add(label, BorderLayout.PAGE_START);
                    buttonPanel.add(returnMenu);
                    pausePanel.add(buttonPanel);
                    pauseMenu.add(pausePanel, BorderLayout.CENTER);
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
        };
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

    /**
     * Displays the board
     */
    public void display() {
        if (type.equals("battleship")) {
            battleshipDisp();
        } else if (type.equals("settlers")) {
            settlersDisp();
        } else {
            player1View = new JFrame();
            player2View = new JFrame();
            disp.setSize(1280, 1024);

            disp.setUndecorated(true);
            player1View.setSize(1280, 1024);
            player2View.setLocation(0, -1024);
            disp.setLocation(0, 0);
            player2View.setLocation(0, 1024);
            player2View.setSize(1280, 1024);
            gameWindow.setPreferredSize(new Dimension(1280, 1024));
            gameWindow.setBorder(new EmptyBorder(0, 128, 0, 128));
            player1View.setExtendedState(JFrame.MAXIMIZED_BOTH);
            player2View.setExtendedState(JFrame.MAXIMIZED_BOTH);
            disp.setExtendedState(JFrame.MAXIMIZED_BOTH);

            disp.add(gameWindow);
            disp.setVisible(true);
            player1View.setVisible(true);
            player2View.setVisible(true);
        }
    }

    /**
     * Displays the proper display for displaying Battleship's display
     * Each player gets a BoardPanel with their grid, buttons in the middle to spawn pegs
     * Ships start off there by default, mostly because I'll be damned if I add 72 buttons
     * at 3AM
     * If you want to add 72 buttons, go for it
     *
     */
    private void battleshipDisp () {
        disp.setSize(1280, 1024);
        disp.setUndecorated(true);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Color battleshipGrey = new Color(14,47,84);

        // Create the necessary panels
        this.player1View = new JFrame();
        player1View.setLayout(new BoxLayout(player1View.getContentPane(), BoxLayout.X_AXIS));
        player1View.setMaximumSize(new Dimension(1024, 1024));
        player1View.setUndecorated(true);
        player1View.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.player2View = new JFrame();
        player2View.setLayout(new BoxLayout(player2View.getContentPane(), BoxLayout.X_AXIS));
        player2View.setSize(1024, 1024);
        player2View.setUndecorated(true);
        player2View.setExtendedState(JFrame.MAXIMIZED_BOTH);
        player1View.addKeyListener(pauseMenuListener());
        player2View.addKeyListener(pauseMenuListener());
        player2View.setFocusable(true);
        player1View.setFocusable(true);

        player1Tray = new JPanel(new BorderLayout());
        player1Tray.setPreferredSize(new Dimension(256, 1024));
        player1Tray.setBackground(battleshipGrey);
        player2Tray = new JPanel(new BorderLayout());
        player2Tray.setPreferredSize(new Dimension(256, 1024));
        player2Tray.setBackground(battleshipGrey);





        try {
            player1Board = new BoardPanel(createImageIcon("playerview1.png", "player 1 view"));
            player2Board = new BoardPanel(createImageIcon("playerview1.png", "player 2 view"));
            player1Board.setPreferredSize(new Dimension(1024, 1024));
            player2Board.setPreferredSize(new Dimension(1024, 1024));
            player1View.add(player1Board, BorderLayout.CENTER);
            player2View.add(player2Board, BorderLayout.CENTER);

            // Buttons for peg spawning
            JButton whitePegs1 = new JButton("White Peg");
            whitePegs1.setPreferredSize(new Dimension(100, 100));
            JButton redPegs1 = new JButton("Red Peg");
            redPegs1.setPreferredSize(new Dimension(100, 100));
            whitePegs1.addActionListener(bigActionListener("white peg", player1Board, true));
            redPegs1.addActionListener(bigActionListener("red peg", player1Board, true));
            player1Tray.add(whitePegs1, BorderLayout.PAGE_START);
            player1Tray.add(redPegs1, BorderLayout.PAGE_END);
        } catch (Exception e) {
            System.out.println(e);
        }
        player1View.add(player1Tray);
        player2View.add(player2Tray);



        JButton whitePegs2 = new JButton("White Peg");
        whitePegs2.setPreferredSize(new Dimension(100, 100));
        JButton redPegs2 = new JButton("Red Peg");
        redPegs2.setPreferredSize(new Dimension(100, 100));
        whitePegs2.addActionListener(bigActionListener("white peg", player2Board, false));
        redPegs2.addActionListener(bigActionListener("red peg", player2Board, false));
        player2Tray.add(whitePegs2, BorderLayout.PAGE_START);
        player2Tray.add(redPegs2, BorderLayout.PAGE_END);

        disp.add(gameWindow);
        player1View.setLocation(0, -1024);
        disp.setLocation(0, 0);
        player2View.setLocation(0, 1024);
        player2View.setVisible(true);
        player1View.setVisible(true);
        disp.setVisible(true);
    }

    /**
     * Displays the proper components for Settlers of Catan
     */
    private void settlersDisp () {
        // Set up Frames
        player1View = new JFrame();
        player1View.setLayout(new BorderLayout());
        player2View = new JFrame();
        player2View.setLayout(new BorderLayout());
        disp.setUndecorated(true);
        player1View.setUndecorated(true);
        player2View.setUndecorated(true);
        disp.setLocation(0, 0);

        player1View.setSize(1280, 1024);
        player2View.setSize(1280, 1024);
        player1View.setLocation(0, 1024);
        player2View.setLocation(0, -1024);


        gameWindow.setPreferredSize(new Dimension(1280, 1024));
        disp.setExtendedState(Frame.MAXIMIZED_BOTH);
        player1View.setExtendedState(Frame.MAXIMIZED_BOTH);
        player2View.setExtendedState(Frame.MAXIMIZED_BOTH);

        // Oh yeah, it's button time
        // Create two panels to house the vast amount of buttons
        JPanel thyHolyButtonHolder1 = new JPanel(new GridLayout(3, 2));
        thyHolyButtonHolder1.setBorder(new EmptyBorder(0, 200, 0, 200));
        JPanel thyHolyButtonHolder2 = new JPanel(new GridLayout(3, 2));
        thyHolyButtonHolder2.setBorder(new EmptyBorder(0, 200, 0, 200));
        JPanel deckPanel1 = new JPanel();
        JPanel deckPanel2 = new JPanel();

        // Create the vast amount of buttons
        // Roads
        JButton d1Road1 = new JButton("Diagonal Road");
        JButton d2Road1 = new JButton("Diagonal Road");
        JButton hRoad1 = new JButton("Horizontal Road");
        JButton vRoad1 = new JButton("Vertical Road");

        JButton d1Road2 = new JButton("Diagonal Road");
        JButton d2Road2 = new JButton("Diagonal Road");
        JButton hRoad2 = new JButton("Horizontal Road");
        JButton vRoad2 = new JButton("Vertical Road");

        // Cities
        JButton cities1 = new JButton("Cities");
        JButton cities2 = new JButton("Cities");

        // Settlements
        JButton settlements1 = new JButton("Settlements");
        JButton settlements2 = new JButton("Settlements");
        JButton endTurn1 = new JButton("End Turn");
        JButton endTurn2 = new JButton("End Turn");


        // Bless the buttons with action listeners
        d1Road1.addActionListener(bigActionListener("roadsd1", gameWindow, true));
        d2Road1.addActionListener(bigActionListener("roadsd2", gameWindow,true));
        hRoad1.addActionListener(bigActionListener("roadsh", gameWindow,true));
        vRoad1.addActionListener(bigActionListener("roadsv", gameWindow,true));

        d1Road2.addActionListener(bigActionListener("roadsd1", gameWindow,false));
        d2Road2.addActionListener(bigActionListener("roadsd2", gameWindow, false));
        hRoad2.addActionListener(bigActionListener("roadsh", gameWindow,false));
        vRoad2.addActionListener(bigActionListener("roadsv", gameWindow,false));

        cities1.addActionListener(bigActionListener("cities", gameWindow,true));
        cities2.addActionListener(bigActionListener("cities", gameWindow,false));

        settlements1.addActionListener(bigActionListener("cities", gameWindow,true));
        settlements2.addActionListener(bigActionListener("settlements", gameWindow,false));

        endTurn1.addActionListener(bigActionListener("endTurn", player1Tray, true));
        endTurn2.addActionListener(bigActionListener("endTurn", player2Tray,false));

        // Add things to player displays
        thyHolyButtonHolder1.add(d1Road1);
        thyHolyButtonHolder1.add(d2Road1);
        thyHolyButtonHolder1.add(hRoad1);
        thyHolyButtonHolder1.add(vRoad1);
        thyHolyButtonHolder1.add(cities1);
        thyHolyButtonHolder1.add(settlements1);
        thyHolyButtonHolder1.add(endTurn1);

        thyHolyButtonHolder2.add(d1Road2);
        thyHolyButtonHolder2.add(d2Road2);
        thyHolyButtonHolder2.add(hRoad2);
        thyHolyButtonHolder2.add(vRoad2);
        thyHolyButtonHolder2.add(cities2);
        thyHolyButtonHolder2.add(settlements2);
        thyHolyButtonHolder2.add(endTurn2);

        deckPanel1.add(thyHolyButtonHolder1, BorderLayout.PAGE_END);
        deckPanel2.add(thyHolyButtonHolder2);

        player1View.add(deckPanel1);
        player2View.add(deckPanel2);

        disp.add(gameWindow);
        player1View.setVisible(true);
        player2View.setVisible(true);
        disp.setVisible(true);
    }

    /**
     * Creates an action listener for Thy Holy Button Holder
     * @return An action listener for Thy Holy Button Holder
     */
    private ActionListener bigActionListener (String inputType, JPanel panelDisplay, boolean owner) {
        ActionListener bigAl = e -> {
            PiecePanel visual;
            switch (inputType) {
                case "D1Roads":
                     visual = new PiecePanel(new Piece(0,0, false, "roadsd1"));
                    if (owner) {
                     visual = new PiecePanel(new Piece(0,0, true, "roadsd1"));
                    }
                    break;
                case "D2Roads":
                    visual = new PiecePanel(new Piece(0,0, false, "roadsd2"));
                    if (owner) {
                        visual = new PiecePanel(new Piece(0,0, true, "roadsd2"));
                    }
                    break;
                case "HRoads":
                    visual = new PiecePanel(new Piece(0,0, false, "roadsh"));
                    if (owner) {
                        visual = new PiecePanel(new Piece(0,0, true, "roadsh"));
                    }
                    break;
                case "VRoads":
                    visual = new PiecePanel(new Piece(0,0, false, "roads"));
                    if (owner) {
                        visual = new PiecePanel(new Piece(0,0, true, "roads"));
                    }
                    break;
                case "Settlements":
                    visual = new PiecePanel(new Piece(0,0, false, "settlements"));
                    if (owner) {
                        visual = new PiecePanel(new Piece(0, 0, true, "settlements"));
                    }
                    break;
                case "Cities":
                    visual = new PiecePanel(new Piece(0,0, false, "cities"));
                    if (owner) {
                        visual = new PiecePanel(new Piece(0, 0, true, "cities"));
                    }
                    break;
                case "white peg":
                    if (owner) {
                        visual = new PiecePanel(new Piece(0, 0, true, "whitepeg"));
                    } else {
                        visual = new PiecePanel(new Piece(0, 0, false, "whitepeg"));
                    }
                    break;
                case "red peg":
                    if (owner) {
                        visual = new PiecePanel(new Piece(0, 0, true, "redpeg"));
                    } else {
                        visual = new PiecePanel(new Piece(0, 0, false, "redpeg"));
                    }
                    break;
                default:
                    visual = new PiecePanel(new Piece(0, 0, true, "robber"));
            }
            panelDisplay.add(visual);
            panelDisplay.revalidate();
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
            if (board.getDescription().contains("player")) {
                graphic.clearRect(0, 0, 1024, 1024);
                graphic.drawImage(board.getImage(), 0, 0, 1024, 1024, this);
            } else {
                graphic.clearRect(0, 0, 1024, 1024);
                graphic.drawImage(board.getImage(), 0, 0, 1280, 1024, this);
            }
        }
    }
}