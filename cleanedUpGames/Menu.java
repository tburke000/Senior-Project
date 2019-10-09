package cleanedUpGames;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends MenuListener{
	//static 
	static JButton chekkers = new JButton("Checkers");
	static JButton chess = new JButton("Chess");
	static JButton navalWarfare = new JButton("Naval Warfare");
	static JButton load = new JButton("Load");
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
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("KNIGHTLY PARTY TABLE");
		titlepanel.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		MenuListener li = new MenuListener();
		
		chekkers.addActionListener(li);
		chess.addActionListener(li);
	    navalWarfare.addActionListener(li);
	    load.addActionListener(li);
	    sd.addActionListener(li);
	    
	    panel.add(chekkers);
	    panel.add(chess);
	    panel.add(navalWarfare);
	    panel.add(load);
	    panel.add(sd);
	    
	    titlepanel.add(title, BorderLayout.NORTH);
	    titlepanel.add(panel, BorderLayout.CENTER);
	    
	    frame.add(titlepanel);
	    frame.setSize(3840, 1024);
	    
		return frame;
	}	
}
