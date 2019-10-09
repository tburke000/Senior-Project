package cleanedUpGames;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class DragListener extends MouseInputAdapter {
    Point location;
    MouseEvent pressed;
 
    public void mousePressed(MouseEvent me){
        pressed = me;
    }
 
    public void mouseDragged(MouseEvent me){
    	int deleteX = 575,deleteY = 890;
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
        
        if((x >= (deleteX - 50) && x <= (deleteX + 100))  && y >= deleteY){
        	Game.gameWindow.remove(component);
        	for (int i=2;i< GameIO.saveArray.size(); i++){
        		if(GameIO.saveArray.get(i).getxAxis() >= deleteX && GameIO.saveArray.get(i).getyAxis() >= deleteY){
        			GameIO.saveArray.remove(i);
        		}
        		
        	}
        } 
     }
}