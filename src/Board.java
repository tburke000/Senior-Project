import jdk.jfr.FlightRecorder;

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
    // JFrame to store the board and display it
    public JFrame disp;
    // JProbably doesn't belong here, but here's the graphics environment for the table
    private GraphicsDevice[] graphicsDevices;
    public Board (String type) {
        this.board = new Square[8][8];
        this.type = type;
        Square square;
        this.graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

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
            disp.setSize(1280, 1024);
            disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
            disp.setUndecorated(true);

            gameWindow.setPreferredSize(new Dimension(1280, 1024));
            gameWindow.setBorder(new EmptyBorder(0, 128, 0, 128));

            disp.add(gameWindow);
            disp.setVisible(true);
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
        disp.setLocationRelativeTo(null);
        disp.setUndecorated(true);
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);



        // Mixing it up, and will probably implement in Settlers - this just seems like a better
        // solution
        JFrame player1View = new JFrame();
        player1View.setSize(1280, 1024);
        player1View.setUndecorated(true);
        player1View.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame player2View = new JFrame();
        player2View.setSize(1280, 1024);
        player2View.setUndecorated(true);
        player2View.setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            BoardPanel player1Board = new BoardPanel(new ImageIcon(ImageIO.read(new File("/battleship2.png"))));
            BoardPanel player2Board = new BoardPanel(boardDisplay);
            player1View.add(player1Board);
            player2View.add(player2Board);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Apparently Battleship2 doesn't exist");
        }

        player2View.setVisible(true);
        player1View.setVisible(true);
        //disp.add(gameWindow);
        disp.setVisible(true);
    }

    /**
     * Displays the proper components for Settlers of Catan
     */
    private void settlersDisp () {
        disp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        disp.setUndecorated(true);
        disp.setLayout(new BoxLayout(disp.getContentPane(), BoxLayout.Y_AXIS));
        disp.setSize(new Dimension(1280, 1024));

        // Create the needed panels
        JPanel player1View = new JPanel(new BorderLayout());
        player1View.setMaximumSize(new Dimension(1280, 1024));

        JPanel player2View = new JPanel(new BorderLayout());
        player2View.setMaximumSize(new Dimension(1280, 1024));
        BoardPanel gameWindow = new BoardPanel(boardDisplay);
        gameWindow.setMaximumSize(new Dimension(1280, 1024));

        // Create two panels to house the vast amount of buttons
        JPanel thyHolyButtonHolder1 = new JPanel(new GridLayout(3, 2));
        thyHolyButtonHolder1.setBorder(new EmptyBorder(0, 200, 0, 200));
        JPanel thyHolyButtonHolder2 = new JPanel(new GridLayout(3, 2));
        thyHolyButtonHolder2.setBorder(new EmptyBorder(0, 200, 0, 200));

        // Create the vast amount of buttons
        // Roads
        JButton d1Road1 = new JButton("Diagonal Road");
        JButton d2Road1 = new JButton("Diagonal Road");
        JButton hRoad1 = new JButton("Horizontal Road");
        JButton vRoad1 = new JButton("Horizontal Road");

        JButton d1Road2 = new JButton("Diagonal Road");
        JButton d2Road2 = new JButton("Diagonal Road");
        JButton hRoad2 = new JButton("Horizontal Road");
        JButton vRoad2 = new JButton("Horizontal Road");

        // Cities
        JButton cities1 = new JButton("Cities");
        JButton cities2 = new JButton("Cities");

        // Settlements
        JButton settlements1 = new JButton("Settlements");
        JButton settlements2 = new JButton("Settlements");

        // Bless the buttons with action listeners
        d1Road1.addActionListener(bigActionListener("roadsd1", true));
        d2Road1.addActionListener(bigActionListener("roadsd2", true));
        hRoad1.addActionListener(bigActionListener("roadsh", true));
        vRoad1.addActionListener(bigActionListener("roadsv", true));

        d1Road2.addActionListener(bigActionListener("roadsd1", false));
        d2Road2.addActionListener(bigActionListener("roadsd2", false));
        hRoad2.addActionListener(bigActionListener("roadsh", false));
        vRoad2.addActionListener(bigActionListener("roadsv", false));

        cities1.addActionListener(bigActionListener("cities", true));
        cities2.addActionListener(bigActionListener("cities", false));

        settlements1.addActionListener(bigActionListener("cities", true));
        settlements2.addActionListener(bigActionListener("settlements", false));

        // Add things to player displays
        thyHolyButtonHolder1.add(d1Road1);
        thyHolyButtonHolder1.add(d2Road1);
        thyHolyButtonHolder1.add(hRoad1);
        thyHolyButtonHolder1.add(vRoad1);
        thyHolyButtonHolder1.add(cities1);
        thyHolyButtonHolder1.add(settlements1);

        // Add everything to the display
        player1View.add(thyHolyButtonHolder1);
        player2View.add(thyHolyButtonHolder2);
        disp.add(gameWindow);
        disp.add(player1View);
        disp.setVisible(true);
    }

    /**
     * Creates an action listener for Thy Holy Button Holder
     * @return An action listener for Thy Holy Button Holder
     */
    private ActionListener bigActionListener (String inputType, boolean owner) {
        ActionListener bigAl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PiecePanel road;
                switch (inputType) {
                    case "D1Roads":
                         road = new PiecePanel(new Piece(0,0, false, "roadsd1"));
                        if (owner) {
                         road = new PiecePanel(new Piece(0,0, true, "roadsd1"));
                        }
                        gameWindow.add(road);
                        break;
                    case "D2Roads":
                        road = new PiecePanel(new Piece(0,0, false, "roadsd2"));
                        if (owner) {
                            road = new PiecePanel(new Piece(0,0, true, "roadsd2"));
                        }
                        gameWindow.add(road);
                        break;
                    case "HRoads":
                        road = new PiecePanel(new Piece(0,0, false, "roadsh"));
                        if (owner) {
                            road = new PiecePanel(new Piece(0,0, true, "roadsh"));
                        }
                        gameWindow.add(road);
                        break;
                    case "VRoads":
                        road = new PiecePanel(new Piece(0,0, false, "roads"));
                        if (owner) {
                            road = new PiecePanel(new Piece(0,0, true, "roads"));
                        }
                        gameWindow.add(road);
                        break;
                    case "Settlements":
                        PiecePanel settlements = new PiecePanel(new Piece(0,0, false, "settlements"));
                        if (owner) {
                            settlements = new PiecePanel(new Piece(0, 0, true, "settlements"));
                        }
                        gameWindow.add(settlements);
                        break;
                    case "Cities":
                        PiecePanel cities = new PiecePanel(new Piece(0,0, false, "cities"));
                        if (owner) {
                            cities = new PiecePanel(new Piece(0, 0, true, "cities"));
                        }
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