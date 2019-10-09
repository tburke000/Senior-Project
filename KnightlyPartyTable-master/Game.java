package cleanedUpGames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game {
	//create frame
	public static JFrame gameWindow = new JFrame();
	
	public static void main(String[] args) throws IOException {
		//Sends name of file to be loaded
		//GameIO.loadIO("Checkers_New_Game");
		//GameIO.loadIO("Battleship_Private_New_Game");
		GameIO.loadIO("Battleship_Public_New_Game");
		
		//Add background,buttons, pieces, and other images to frame
		gameWindow.setLayout(null);
		gameWindow.setContentPane(insertBackground());
		savebutton();
		deletePicture();
		printDoodads();
		
		//Set options on frame and display it	
		gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameWindow.setUndecorated(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
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
	
	public static void addDragListener(JLabel jl){
		DragListener dList = new DragListener();
		jl.addMouseListener(dList);
		jl.addMouseMotionListener(dList);
	}

	public static Dimension getScreenDimension(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public static void printDoodads() throws IOException{
		JLabel jl = new JLabel();
		

		//Loops through array of Doodads, turns them into labels and adds them to the frame
		for (int i=2;i<GameIO.loadArray.size(); i++){
			//Get Doodad from array created from load file
			Doodad dd = GameIO.loadArray.get(i);
			//Turn Doodad into JLabel and set options
			jl = ddToJLabel(GameIO.loadArray.get(i));
			addDragListener(jl);
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
	
	public static void savebutton(){
		JButton save = new JButton("Save");
		ActionListener listener = new ClickListener();
	
		save.addActionListener(listener);
		save.setBounds(5, 5, 90, 50);
		
		gameWindow.add(save);
	}
}