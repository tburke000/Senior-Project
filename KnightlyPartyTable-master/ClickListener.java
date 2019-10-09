package cleanedUpGames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class ClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e){
		try {
			GameIO.saveIO();
			} catch (IOException e1){
				e1.printStackTrace();
				}
	}
}