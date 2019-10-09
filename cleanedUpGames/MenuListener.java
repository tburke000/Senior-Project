package cleanedUpGames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class MenuListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if (source == Menu.chekkers)
		{
			try {
				Game.main("C:/NewGame/Checkers_New_Game.csv");
				Menu.jf.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(source == Menu.chess)
		{
			try {
				Game.main("C:/NewGame/Chess_New_Game.csv");
				Menu.jf.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(source == Menu.navalWarfare)
		{
			try {
				Game.main("C:/NewGame/Battleships_New_Game.csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if(source==Menu.load)
		{
			LoadGUI loadgui = new LoadGUI();


		}
		else if(source == Menu.sd){
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

