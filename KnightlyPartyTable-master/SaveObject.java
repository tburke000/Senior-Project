package cleanedUpGames;

import javax.swing.JLabel;

public class SaveObject {
	int xAxis, yAxis;
	String imageFile;
	JLabel saveLabel;
	
	public SaveObject(JLabel saveLabel, String imageFile){
		this.imageFile = imageFile;
		this.saveLabel = saveLabel;
	}
	
	public int getxAxis(){
		xAxis = saveLabel.getX();
		return xAxis;
	}
	
	public int getyAxis(){
		yAxis = saveLabel.getY();
		return yAxis;
	} 
	
	public String getimageFile(){
		return imageFile;
	}
	

}
