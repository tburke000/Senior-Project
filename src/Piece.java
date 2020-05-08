import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * A class representing a piece in a board game. Used for Chess, Checkers, and Battleship
 * @author burke
 */
public class Piece {
    // Boolean representing who owns the piece. True for Player One, False for Player Two.
    private boolean owner;
    // String representing the type of piece. Will be used in check methods depending on the game.
    private String type;
    // Image representing the piece
    public BufferedImage image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
    // Square representing the position
    public Square pos;
    // Ints representing the X and Y positions of the piece
    private int xAxis, yAxis;

    public Piece (int xAxis, int yAxis, boolean owner, String type) {
        System.out.println("Spinning up piece at " + xAxis + ", " + yAxis);
        this.owner = owner;
        setType(type);
        this.pos = new Square(0, 0); // Default position at 0,0
        try {
            switch (type) {
                case "checker":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("RedChecker.png"));
                        System.out.println("Image Found: Red Checker");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackChecker.png"));
                        System.out.println("Image Found: Black Checker");
                    }
                    break;
                case "rook":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhiteRook.png"));
                        System.out.println("Image Found: White Rook");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackRook.png"));
                        System.out.println("Image Found: Black Rook");
                    }
                    break;
                case "knight":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhiteKnight.png"));
                        System.out.println("Image Found: White Knight");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackKnight.png"));
                        System.out.println("Image Found: Black Knight");
                    }
                    break;
                case "bishop":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhiteBishop.png"));
                        System.out.println("Image Found: White Bishop");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackBishop.png"));
                        System.out.println("Image Found: Black Bishop");
                    }
                    break;
                case "queen":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhiteQueen.png"));
                        System.out.println("Image Found: White Queen");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackQueen.png"));
                        System.out.println("Image Found: Black Queen");
                    }
                    break;
                case "king":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhiteKingChess.png"));
                        System.out.println("Image Found: White King");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackKingChess.png"));
                        System.out.println("Image Found: Black King");
                    }
                    break;
                case "pawn":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("WhitePawn.png"));
                        System.out.println("Image Found: White Pawn");
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("BlackPawn.png"));
                        System.out.println("Image Found: Black Pawn");
                    }
                    break;
                case "carrier":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("Carrier.png"));
                    System.out.println("Image Found: Carrier");
                    break;
                case "battleship":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("BattleshipV.png"));
                    System.out.println("Image Found: Battleship");
                    break;
                case "destroyer":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("DestroyerH.png"));
                    System.out.println("Image Found: Destroyer");
                    break;
                case "cruiser":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("CruiserV.png"));
                    System.out.println("Image Found: Cruiser");
                    break;
                case "submarine":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("SubH.png"));
                    System.out.println("Image Found: Submarine");
                    break;
                case "redpeg":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("RedPeg.png"));
                    System.out.println("Image Found: Red Peg");
                    break;
                case "whitepeg":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("WhitePeg.png"));
                    System.out.println("Image Found: White Peg");
                    break;
                case "Hill":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("hills.png"));
                    System.out.println("Successfully found image for Hills tile");
                    break;
                case "Pasture":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("pasture.png"));
                    System.out.println("Successfully found image for Pasture tile");
                    break;
                case "Mountain":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("mountains.png"));
                    System.out.println("Successfully found image for Mountain tile");
                    break;
                case "Field":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("fields.png"));
                    System.out.println("Successfully found image for Fields tile");
                    break;
                case "Forest":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("forests.png"));
                    System.out.println("Successfully found image for Forest tile");
                    break;
                case "Knight":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("knight.png"));
                    System.out.println("Image Found: Knight Card");
                    break;
                case "Victory Point":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("victory point.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Road Building":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("road building.png"));
                    System.out.println("Successfully found image for Road Building Card");
                    break;
                case "Year of Plenty":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("year of plenty.png"));
                    System.out.println("Successfully found image for Victory Point Card");
                    break;
                case "Monopoly":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("monopoly.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "roadsd1":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road1d1.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road2d1.png"));
                    }
                    break;
                case "roadsd2":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road1d2.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road2d2.png"));
                    }
                    break;
                case "roadsh":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road1h.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road2h.png"));
                    }
                    break;
                case "roadsv":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road1v.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("road2v.png"));
                    }
                    break;
                case "settlements":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("settlement1.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("settlement2.png"));
                    }
                    break;
                case "cities":
                    if (owner) {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("city1.png"));
                    } else {
                        this.image = ImageIO.read(this.getClass().getResourceAsStream("city2.png"));
                    }
                    break;
                case "brick":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("brick.png"));
                    break;
                case "grain":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("grain.png"));
                    break;
                case "lumber":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("lumber.png"));
                    break;
                case "ore":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("ore.png"));
                    break;
                case "wool":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("wool.png"));
                    break;
                case "robber":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("robber.png"));
                    break;
                case "Desert":
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("desert.png"));
                    break;
                default:
                    this.image = ImageIO.read(this.getClass().getResourceAsStream("uhoh.png"));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e + ": Error finding image");
        }

        this.xAxis = xAxis;
        this.yAxis = yAxis;

    }

    public void setType (String type) { this.type = type; }

    public String getType () {
        return type;
    }

    public boolean getOwner (){ return owner; }

    public void setPosition (int row, int column) {
        this.pos.setPos(row, column);
    }

    /**
     * Returns the position the piece currently resides on as a Square
     * @return A Square containing the current row and column of the piece
     */
    public Square getPosition () {
        return this.pos;
    }
        //getters and setters
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
    }

