import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * A panel used to represent a piece on the game board. I'm probably waaaay overcomplicating things
 */
public class PiecePanel extends JComponent implements ImageObserver {
    private Image _image;
    private Piece _piece;
    // Necessities for draggable component
    private Boolean draggable = true;
    protected Point anchorPoint;
    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    protected Boolean overbearing = true;

    public PiecePanel (Piece piece) {
        addDragListeners();
        _image = piece.getImage();
        _piece = piece;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.clearRect(0, 0, 80, 80);
        if (_image != null) {
            graphic.drawImage(_image, 0, 0, 80, 80, this);
        }
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

                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }

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
