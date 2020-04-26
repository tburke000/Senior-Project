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
    protected Point initialPoint;
    protected Point anchorPoint;
    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    protected Boolean overbearing = true;

    public PiecePanel (Piece piece) {
        _image = piece.getImage();
        _piece = piece;
        initialPoint = new Point(piece.getxAxis(), piece.getyAxis());
        addDragListeners();
    }

    /**
     * Paints the graphic of a piece onto the piece panel
     * @param g
     */
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
                    case "cruiser":
                        graphic.drawImage(_image, 0, 0, 80, 240, this);
                        break;
                    default:
                        graphic.drawImage(_image, 0, 0, 240, 80, this);
                }
            } else if (isTile(_piece)) {
                graphic.drawImage(_image,0,0, 222, 256, this);
            } else if (isCard(_piece)) {
                graphic.drawImage(_image,0,0,160,236, this);
            } else {
                graphic.drawImage(_image, 0, 0, 80, 80, this);
            }
        }
    }

    /**
     * As indicative of the name, adds a drag listener to the panel
     */
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

            @Override
            public void mouseReleased(MouseEvent e) {
                int possibleX = anchorPoint.x;
                int possibleY = anchorPoint.y;

                switch (_piece.getType()){
                    case "checkers":
                        if (Checkers.move(_piece, possibleX, possibleY)) {
                            Point newPoint = new Point(possibleX, possibleY);
                            setLocation(newPoint);
                            initialPoint = newPoint;
                        }
                }
            }
        });
    }

    /**
     * Overrides the original getPrefferedSize based on the type of piece that the piece panel will represent.
     * @return a Dimension of the size of the image.
     */
    @Override
    public Dimension getPreferredSize() {
        if (isShip(_piece)) {
            switch (_piece.getType()){
                case "carrier":
                    return new Dimension(400, 80);
                case "battleship":
                    return new Dimension(80, 320);
                case "cruiser":
                    return new Dimension(80, 240);
                case "hex":
                    return new Dimension(222, 260);
                default:
                    return new Dimension(240, 80);
            }
        } else if (isTile(_piece)){
            return new Dimension(222, 256);
        } else if (isCard(_piece)) {
            return new Dimension(160,236);
        } else {
            return new Dimension(80, 80);
        }
    }

    /**
     * Not used as of yet, but would remove the drag listener from the piece panel
     */
    private void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Pretty standard, determines if a piece is a ship for Battleship. Mostly to keep above code cleaner.
     * @param _piece the piece in question
     * @return True if the piece is a ship, false if not. Never empty, null, or 0.
     */
    private boolean isShip(Piece _piece) {
        if (_piece.getType().equals("carrier") || _piece.getType().equals("battleship") || _piece.getType().equals("destroyer") || _piece.getType().equals("cruiser") || _piece.getType().equals("submarine")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Also pretty standard, determines if a piece is a tile in Settlers.
     * @param _piece the piece in question
     * @return True if the piece is from Settlers, false if not.
     */
    private boolean isTile(Piece _piece) {
        if (_piece.getType().equals("Hill") || _piece.getType().equals("Pasture") || _piece.getType().equals("Forest") || _piece.getType().equals("Mountain") || _piece.getType().equals("Field")|| _piece.getType().equals("Desert")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCard (Piece _piece) {
        if (_piece.getType().equals("ore") || _piece.getType().equals("brick") || _piece.getType().equals("grain") || _piece.getType().equals("lumber") || _piece.getType().equals("wool")) {
            return true;
        } else {
            return false;
        }
    }

}
