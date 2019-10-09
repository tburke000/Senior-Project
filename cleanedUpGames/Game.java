package cleanedUpGames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game {
	//create frame
	public static JFrame gameWindow = new JFrame();
	public static JButton save = new JButton("Save");
	public static JButton quit = new JButton("Quit");
	public static JButton addMenu = new JButton("Add");
	public static JButton addMenu2 = new JButton("Add");
	
	public static void main(String gameFile) throws IOException {
		//Sends name of file to be loaded
		GameIO.loadIO(gameFile);
		
		//Add background,buttons, pieces, and other images to frame
		gameWindow.setLayout(null);
		gameWindow.setContentPane(insertBackground());
		addButton();
		deletePicture();
		printDoodads();
		
		//Set options on frame and display it	
		gameWindow.setSize(3840, 1024);
		gameWindow.setUndecorated(true);
		//gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   GameIO.loadArray.clear();
				   gameWindow.dispose();
			   }
			  });

		gameWindow.setVisible(true);
	}
	
	public static void addDoodad(String imageFile, int x, int y) throws IOException{
		JLabel jl = new JLabel();
		Doodad dd = new Doodad(x,y,imageFile);
		jl =ddToJLabel(dd);
		addGameListener(jl);
		Dimension size= jl.getPreferredSize();
		jl.setBounds(dd.getxAxis(), dd.getyAxis(), size.width, size.height);
		
		//Add JLabel and and file path name to an array for saving game
		GameIO.saveArray.add(new SaveObject(jl, dd.getName()));
		//Add JLabel to frame
		gameWindow.add(jl);
		gameWindow.repaint();
	}
	
	//Get image file for game board background and convert it to a JLabel
	public static JLabel insertBackground() throws IOException {
		JLabel jl = new JLabel();
		Doodad dd = GameIO.loadArray.get(0);
		jl = ddToJLabel(dd);
		GameIO.saveArray.add(new SaveObject(jl, dd.getName()));
		
		return jl;	
	}
	//create label with picture to indicate delete area
	public static void deletePicture() throws IOException{	
			JLabel jl = new JLabel();
			Doodad dd = GameIO.loadArray.get(1);
			
			jl = ddToJLabel(dd);
			GameIO.saveArray.add(new SaveObject(jl, dd.getName()));
			Dimension size= jl.getPreferredSize();
			jl.setBounds(dd.getxAxis(), dd.getyAxis(), size.width, size.height);
			
			gameWindow.add(jl);
	} 
	
	public static void addGameListener(JLabel jl){
		GameListener dList = new GameListener();
		jl.addMouseListener(dList);
		jl.addMouseMotionListener(dList);
	}
	
	public static void printDoodads() throws IOException{
		JLabel jl = new JLabel();
		

		//Loops through array of Doodads, turns them into labels and adds them to the frame
		for (int i=2;i<GameIO.loadArray.size(); i++){
			//Get Doodad from array created from load file
			Doodad dd = GameIO.loadArray.get(i);
			//Turn Doodad into JLabel and set options
			jl = ddToJLabel(GameIO.loadArray.get(i));
			addGameListener(jl);
			Dimension size= jl.getPreferredSize();
			jl.setBounds(dd.getxAxis(), dd.getyAxis(), size.width, size.height);
			
			//Add JLabel and and file path name to an array for saving game
			GameIO.saveArray.add(new SaveObject(jl, dd.getName()));
			//Add JLabel to frame
			gameWindow.add(jl);		
		}
	}
	
	public static JLabel ddToJLabel(Doodad dd) throws IOException{
		BufferedImage image = ImageIO.read(new File(dd.getName()));
		JLabel jl = new JLabel(new ImageIcon(image));
		jl.setLocation(dd.getxAxis(),dd.getyAxis() );
		
		return jl;
	}
	
	public static void addButton(){
		//JButton save = new JButton("Save");
		GameListener listener = new GameListener();
		save.addMouseListener(listener);
		save.setBounds(1785, 5, 90, 50);
		quit.addMouseListener(listener);
		quit.setBounds(1975,5, 90, 50);
		addMenu.addMouseListener(listener);
		addMenu.setBounds(5, 5, 90, 50);
		addMenu2.addMouseListener(listener);
		addMenu2.setBounds(2565, 5, 90, 50);
		
		gameWindow.add(save);
		gameWindow.add(quit);
		gameWindow.add(addMenu);
		gameWindow.add(addMenu2);
	}
	
	public static void menuButton(){
		GameListener listener = new GameListener();
		
		addMenu.addMouseListener(listener);
		addMenu.setBounds(5, 5, 90, 50);
		
		gameWindow.add(addMenu);
	}
}