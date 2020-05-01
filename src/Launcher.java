import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * After a frustrating combined three hours, I've decided to completely start fresh.
 * Welcome to the Knight's Party Table v.II. I hope I comment it better than my
 * predecessors.
 *
 * This class is the basis for the system. It contains a list of all the games on the
 * table, and is the hub of the table.
 *
 * Although slight improvements, primarily legacy code from Version I
 */
public class Launcher {
    //static
    static JButton chekkers = new JButton("Checkers");
    static JButton chess = new JButton("Chess");
    static JButton battleship = new JButton("Battleship");
    static JButton catan = new JButton("Settlers of Catan");
    static JButton sd = new JButton("Shut Down");

    public static JFrame jf = dispMenu();

    public static void main(String[] args){

        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    public static JFrame dispMenu() {

        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel(new BorderLayout());
        try {
            frame.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;


            BufferedImage image = ImageIO.read(new File("src/logo.png"));
            JLabel logo  = new JLabel(new ImageIcon(image));
            logo.setSize(new Dimension(700, 533));
            frame.add(logo, BorderLayout.PAGE_START);


            JLabel title = new JLabel("Knight's Party Table II", SwingConstants.CENTER);

            title.setFont(title.getFont().deriveFont(18.0f));
            titlePanel.add(title, BorderLayout.PAGE_START);
            title.setBorder(new EmptyBorder(100, 10, 0, 10));
            frame.setUndecorated(true);
            MenuListener li = new MenuListener();

            chekkers.addActionListener(li);
            chess.addActionListener(li);
            battleship.addActionListener(li);
            catan.addActionListener(li);
            sd.addActionListener(li);

            panel.add(battleship, gbc);
            panel.add(chekkers, gbc);
            panel.add(chess, gbc);
            panel.add(catan, gbc);
            panel.add(sd, gbc);


            titlePanel.add(panel);

            // Make the colors uniform
            frame.getContentPane().setBackground(Color.WHITE);
            titlePanel.setBackground(Color.WHITE);
            panel.setBackground(Color.WHITE);
            frame.add(titlePanel);
            frame.setSize(1280, 1024);

        } catch (Exception e) {
            System.out.println(e);
        }

        return frame;

    }

    public static void start(Game game) {
        game.start();
    }
}
