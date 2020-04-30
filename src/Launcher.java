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
        frame.setLayout((new GridBagLayout()));
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Knight's Party Table II", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(18.0f));
        titlePanel.setLayout(new BorderLayout());

        try {
            BufferedImage image = ImageIO.read(new File("src/logo.png"));
            ImagePanel logo = new ImagePanel(image);
            logo.setSize(new Dimension(700, 533));
            frame.add(logo, gbc);
        } catch (Exception e)
        {
            System.out.println("I am going to throw myself in front of a skid loader");
        }
        frame.setUndecorated(true);
        MenuListener li = new MenuListener();
        panel.setBorder(new EmptyBorder(15,15,15,15));

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

        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(panel, BorderLayout.CENTER);

        frame.add(titlePanel);
        frame.setSize(1280, 1024);

        return frame;
    }

    public static void start(Game game) {
        game.start();
    }
}

/**
 * Class representing a static image for a JFrame
 * Because Tony is sick of screwing around with JLabels
 * @author burke
 */
class ImagePanel extends JPanel {
    private Image image;
    public ImagePanel (Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
//        graphic.clearRect(0, 0, 700, 533);
        graphic.drawImage(image, 0, 0, 700, 533, this);

    }
}