package cleanedUpGames;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;


public class GameIO {

	public static ArrayList<Doodad> loadArray = new ArrayList<Doodad>();
	public static ArrayList<SaveObject> saveArray = new ArrayList<SaveObject>();
	
	public static  void saveIO() throws IOException 
	{
		
		try{
			Date date = new Date();
			int arraySize = saveArray.size();
			int endOfArray = arraySize - 1;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh_mm_ss");
			String timeStamp = dateFormat.format(date).toString();
			PrintWriter fileWriter = new PrintWriter("C:/Users/Nahrikira/workspace/cleanedUpGames/SavedGames/"+timeStamp+ ".csv","UTF-8");
			 
			for (int i=0;i< arraySize; i++){
				fileWriter.print(saveArray.get(i).getxAxis());
				fileWriter.print(',');
				fileWriter.print(saveArray.get(i).getyAxis());
				fileWriter.print(',');
				fileWriter.print(saveArray.get(i).getimageFile());
				if (i < endOfArray){
					fileWriter.print(',');
				}
				
			}
			fileWriter.close();
			
			} catch (IOException e)
				{
					//Open GUI that displays Error Message
					System.out.println("Error Opening FilePath");
				}		
	}
	
	public static void loadIO(String fileName)
	{
		int x, y;
		String name;
			
		try{
			Scanner scanner = new Scanner (new File("C:/Users/Nahrikira/workspace/cleanedUpGames/SavedGames/" + fileName + ".csv"));
			scanner.useDelimiter(",");
			
			while(scanner.hasNext()){
				x=scanner.nextInt();
				y=scanner.nextInt();
				name =scanner.next();
				loadArray.add(new Doodad(x,y,name));
				}
				scanner.close();
			
			} catch(IOException d){
				System.out.println("Error Opening File Path in Load");
				}
	}
}