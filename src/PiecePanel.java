import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * A panel used to represent a piece on the game board. I'm probably waaaay overcomplicating things
 */
public class PiecePanel extends JComponent {
    private BufferedImage _image;
    private Piece _piece;
    protected Point anchorPoint;
    protected Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);


    public PiecePanel (Piece piece) {
        _image = piece.getImage();
        _piece = piece;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.drawImage(_image, _piece.getxAxis(), _piece.getyAxis(), this);
    }

    private void addDragListeners() {
        final PiecePanel handle = this;
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x - anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(position);
            }
        });
    }

    private void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }
}
