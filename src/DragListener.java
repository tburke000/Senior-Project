import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * Class used to represent a listener for objects throughout hte system.
 * @author burke
 */
public class DragListener extends MouseInputAdapter {
    Point location;
    MouseEvent pressed;

    public void mousePressed(MouseEvent me){
        pressed = me;
    }

    public void mouseDragged(MouseEvent me){
        int deleteX = 1175;
        int deleteY = 575;
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);

//        if(x >= deleteX && y >= deleteY){
//            Game.gameWindow.remove(component);
//            for (int i=2;i< GameIO.saveArray.size(); i++){
//                if(GameIO.saveArray.get(i).getxAxis() >= deleteX && GameIO.saveArray.get(i).getyAxis() >= deleteY){
//                    GameIO.saveArray.remove(i);
//                }
//
//            }
//        }
    }
}