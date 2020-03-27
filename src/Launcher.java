import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * After a frustrating combined three hours, I've decided to completely start fresh.
 * Welcome to the Knight's Party Table v.II. I hope I comment it better than my
 * predecessors.
 *
 * This class is the basis for the system. It contains a list of all the games on the
 * table, and is the hub of the table.
 */
public class Launcher {
    //static
    static JButton chekkers = new JButton("Checkers");
    static JButton chess = new JButton("Chess");
    static JButton navalWarfare = new JButton("Naval Warfare");
    //static JButton load = new JButton("Load");
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
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Knight's Party Table II", SwingConstants.CENTER);
        titlePanel.setLayout(new BorderLayout());
        frame.setUndecorated(true);
        MenuListener li = new MenuListener();

        chekkers.addActionListener(li);
        chess.addActionListener(li);
        navalWarfare.addActionListener(li);
        sd.addActionListener(li);

        panel.add(chekkers);
        panel.add(chess);
        panel.add(navalWarfare);
        panel.add(sd);

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
