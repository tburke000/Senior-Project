import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;

/**
 * A panel used to represent a piece on the game board. I'm probably waaaay overcomplicating things
 * @author burke
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
        setBackground(new Color(0,0,0,150));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        if (_image != null) {
            if (isShip(_piece)) {
                switch (_piece.getType()){
                    case "carrier":
                        graphic.drawImage(_image, 0, 0, 400, 80, this);
                        break;
                    case "battleship":
                        graphic.drawImage(_image, 0, 0, 80, 320, this);
                        break;
//                    case "submarine":
//                        graphic.drawImage(_image, 0, 0, 240, 80, this);
//                        break;
                    case "cruiser":
                        graphic.drawImage(_image, 0, 0, 80, 240, this);
                        break;
                    default:
                        graphic.drawImage(_image, 0, 0, 240, 80, this);
                }
            } else {
                graphic.drawImage(_image, 0, 0, 80, 80, this);
            }
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
                int possibleX = mouseOnScreen.x - parentOnScreen.x - anchorX;
                int possibleY = mouseOnScreen.y - parentOnScreen.y - anchorY;

                Point position = new Point(possibleX, possibleY);

                setLocation(position);

                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }

            }

        });
    }

    @Override
    public Dimension getPreferredSize() {
        if (isShip(_piece)) {
            switch (_piece.getType()){
                case "carrier":
                    return new Dimension(400, 80);
                case "battleship":
                    return new Dimension(80, 320);
//                case "submarine":
//                    return new Dimension(160, 80);
                case "cruiser":
                    return new Dimension(80, 240);
                default:
                    return new Dimension(240, 80);
            }
        } else {
            return new Dimension(80, 80);
        }
    }

    private void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Pretty standard, determines if a piece is a ship for Battleship. Mostly to keep above code cleaner.
     * @param _piece
     * @return
     */
    private boolean isShip(Piece _piece) {
        if (_piece.getType().equals("carrier") || _piece.getType().equals("battleship") || _piece.getType().equals("destroyer") || _piece.getType().equals("cruiser") || _piece.getType().equals("submarine")) {
            return true;
        } else {
            return false;
        }
    }

}

