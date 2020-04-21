import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        frame.setUndecorated(true);
        MenuListener li = new MenuListener();
        panel.setBorder(new EmptyBorder(15,15,15,15));

        chekkers.addActionListener(li);
        chess.addActionListener(li);
        battleship.addActionListener(li);
        sd.addActionListener(li);
        catan.addActionListener(li);

        panel.add(battleship, gbc);
        panel.add(chekkers, gbc);
        panel.add(chess, gbc);
        panel.add(catan, gbc);
        panel.add(sd, gbc);

        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(panel, BorderLayout.CENTER);

        frame.add(titlePanel);
        frame.setSize(1920, 1080);

        return frame;
    }

    public static void start(Game game) {
        game.start();
    }
}
