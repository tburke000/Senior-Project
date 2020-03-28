import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * A class representing a piece in a board game. Used for Chess, Checkers, and maybe Battleship
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
        this.type = type;
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
            }
        } catch (Exception e) {
            System.out.print("Error finding image");
            System.out.println(e);
        }

        this.xAxis = xAxis;
        this.yAxis = yAxis;


    }

    public void setType (String type) {
        this.type = type;
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

