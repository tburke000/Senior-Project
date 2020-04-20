
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * "Legacy code" from Version I
 */
public class MenuListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0)
    {
        Object source = arg0.getSource();
        if (source == Launcher.chekkers)
        {
                Launcher.start(new Checkers());
                Launcher.jf.dispose();

        }
        else if(source == Launcher.chess)
        {
                Launcher.start(new Chess());
                Launcher.jf.dispose();

        }
        else if(source == Launcher.battleship)
        {
                Launcher.start(new Battleship());
                Launcher.jf.dispose();

        }
        else if(source == Launcher.sd){
            try {
                Runtime.getRuntime().exec("shutdown -s -t 3");
                System.exit(0);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            JFrame errorFrame = new JFrame();

            errorFrame.pack();
            errorFrame.setSize(225,150);
            errorFrame.setLocationRelativeTo(null);
            JLabel error = new JLabel("There was an error. Please try again");
            errorFrame.add(error);
            errorFrame.setVisible(true);
                    }
    }

}

