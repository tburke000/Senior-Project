package cleanedUpGames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Doodad{	
	private int height, width, xAxis, yAxis, frame;
	private String name;
	private BufferedImage image;
//	private Enum visible;
	
//	private enum Visibility{
//		PLAYER_1, PLAYER_2, PLAYER_3
//	}
	
	//Get the image
	
	
	
	
	public Doodad(int xAxis, int yAxis, String name) throws IOException {
		this.name = name;
		//Gets the image 
		File file = new File(name);
		//displays image?
		image = ImageIO.read(file);
		//width and height are set from the picture imported
		width = image.getWidth();
		height = image.getHeight();

		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.frame = frame;
//		this.visible = visible;
	}

	//getters and setters
	public int getFrame() {
		return frame;
	}
	public void setFrame(int Frame) {
		this.frame = frame;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public String getName(){
		return this.name;
	}
}