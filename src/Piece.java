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
                        this.image = ImageIO.read(new File("src/RedChecker.png"));
                        System.out.println("Image Found: Red Checker");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackChecker.png"));
                        System.out.println("Image Found: Black Checker");
                    }
                    break;
                case "rook":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhiteRook.png"));
                        System.out.println("Image Found: White Rook");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackRook.png"));
                        System.out.println("Image Found: Black Rook");
                    }
                    break;
                case "knight":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhiteKnight.png"));
                        System.out.println("Image Found: White Knight");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackKnight.png"));
                        System.out.println("Image Found: Black Knight");
                    }
                    break;
                case "bishop":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhiteBishop.png"));
                        System.out.println("Image Found: White Bishop");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackBishop.png"));
                        System.out.println("Image Found: Black Bishop");
                    }
                    break;
                case "queen":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhiteQueen.png"));
                        System.out.println("Image Found: White Queen");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackQueen.png"));
                        System.out.println("Image Found: Black Queen");
                    }
                    break;
                case "king":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhiteKingChess.png"));
                        System.out.println("Image Found: White King");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackKingChess.png"));
                        System.out.println("Image Found: Black King");
                    }
                    break;
                case "pawn":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/WhitePawn.png"));
                        System.out.println("Image Found: White Pawn");
                    } else {
                        this.image = ImageIO.read(new File("src/BlackPawn.png"));
                        System.out.println("Image Found: Black Pawn");
                    }
                    break;
                case "carrier":
                    this.image = ImageIO.read(new File("src/Carrier.png"));
                    System.out.println("Image Found: Carrier");
                    break;
                case "battleship":
                    this.image = ImageIO.read(new File("src/BattleshipV.png"));
                    System.out.println("Image Found: Battleship");
                    break;
                case "destroyer":
                    this.image = ImageIO.read(new File("src/DestroyerH.png"));
                    System.out.println("Image Found: Destroyer");
                    break;
                case "cruiser":
                    this.image = ImageIO.read(new File("src/CruiserV.png"));
                    System.out.println("Image Found: Cruiser");
                    break;
                case "submarine":
                    this.image = ImageIO.read(new File("src/SubH.png"));
                    System.out.println("Image Found: Submarine");
                    break;
                case "redpeg":
                    this.image = ImageIO.read(new File("src/RedPeg.png"));
                    System.out.println("Image Found: Red Peg");
                    break;
                case "whitepeg":
                    this.image = ImageIO.read(new File("src/WhitePeg.png"));
                    System.out.println("Image Found: White Peg");
                    break;
                case "Hill":
                    this.image = ImageIO.read(new File("src/hills.png"));
                    System.out.println("Successfully found image for Hills tile");
                    break;
                case "Pasture":
                    this.image = ImageIO.read(new File("src/pasture.png"));
                    System.out.println("Successfully found image for Pasture tile");
                    break;
                case "Mountain":
                    this.image = ImageIO.read(new File("src/mountains.png"));
                    System.out.println("Successfully found image for Mountain tile");
                    break;
                case "Field":
                    this.image = ImageIO.read(new File("src/fields.png"));
                    System.out.println("Successfully found image for Fields tile");
                    break;
                case "Forest":
                    this.image = ImageIO.read(new File("src/forests.png"));
                    System.out.println("Successfully found image for Forest tile");
                    break;
                case "Knight":
                    this.image = ImageIO.read(new File("src/knight.png"));
                    System.out.println("Image Found: Knight Card");
                    break;
                case "Victory Point":
                    this.image = ImageIO.read(new File("src/victory point.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Road Building":
                    this.image = ImageIO.read(new File("src/road building.png"));
                    System.out.println("Successfully found image for Road Building Card");
                    break;
                case "Year of Plenty":
                    this.image = ImageIO.read(new File("src/year of plenty.png"));
                    System.out.println("Successfully found image for Victory Point Card");
                    break;
                case "Monopoly":
                    this.image = ImageIO.read(new File("src/monopoly.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "roadsd1":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/road1d1.png"));
                    } else {
                        this.image = ImageIO.read(new File("src/road2d1"));
                    }
                    break;
                case "roadsd2":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/road1d2"));
                    } else {
                        this.image = ImageIO.read(new File("src/road2d2"));
                    }
                    break;
                case "roadsh":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/road1h"));
                    } else {
                        this.image = ImageIO.read(new File("src/road2h"));
                    }
                    break;
                case "roadsv":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/road1v"));
                    } else {
                        this.image = ImageIO.read(new File("src/road2v"));
                    }
                    break;
                case "settlements":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/settlement1.png"));
                    } else {
                        this.image = ImageIO.read(new File("src/settlement2.png"));
                    }
                    break;
                case "cities":
                    if (owner) {
                        this.image = ImageIO.read(new File("src/city1.png"));
                    } else {
                        this.image = ImageIO.read(new File("src/city2.png"));
                    }
                case "brick":
                    this.image = ImageIO.read(new File("src/brick.png"));
                    break;
                case "grain":
                    this.image = ImageIO.read(new File("src/grain.png"));
                    break;
                case "lumber":
                    this.image = ImageIO.read(new File("src/lumber.png"));
                    break;
                case "ore":
                    this.image = ImageIO.read(new File("src/ore.png"));
                    break;
                case "wool":
                    this.image = ImageIO.read(new File("src/wool.png"));
                    break;
                case "robber":
                    this.image = ImageIO.read(new File("src/robber.png"));
                    break;
                default:
                    this.image = ImageIO.read(new File("src/desert.png"));
                    System.out.println("Successfully found image for tile");
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

