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

class ClickListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e){
			try {
				GameIO.saveIO();
			} catch (IOException e1){
				e1.printStackTrace();
				}
	}
	
	
}