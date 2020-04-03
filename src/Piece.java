import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * A class representing a piece in a board game. Used for Chess, Checkers, and maybe Battleship
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
                    this.image = ImageIO.read(new File("src/SubmarineH.png"));
                    System.out.println("Image Found: Submarine");
            }
        } catch (Exception e) {
            System.out.println(e + ": Error finding image");
        }

        this.xAxis = xAxis;
        this.yAxis = yAxis;

    }

    public void setType (String _type) {
        type = _type;
    }

    public void setPosition (int row, int column) {
        this.pos.setPos(row, column);
    }

    /**
     * Returns the position the piece currently resides on as an int array
     * @return And int array returning the current position of the piece
     */
    public int[] getPosition () {
        return this.pos.getPos();
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

