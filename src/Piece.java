import javax.swing.*;
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
    public BufferedImage image;
    // Square representing the position
    public Square pos;
    // Ints representing the X and Y positions of the piece
    private int xAxis, yAxis;

    public Piece (int xAxis, int yAxis, boolean owner, String type) {
        this.owner = owner;
        this.type = type;
        this.pos = new Square(0, 0); // Default position at 0,0
        try {
            switch (type) {
                case "checkers":
                    if (owner) {
                        File rc = new File("RedChecker.png");
                        this.image = ImageIO.read(rc);
                    } else {
                        File bc = new File("BlackChecker.png");
                        this.image = ImageIO.read(bc);
                    }
            }
        } catch (Exception e) {
            System.out.print("Error finding image");
        }

        this.xAxis = xAxis;
        this.yAxis = yAxis;


    }

    public void setPosition (int row, int column) {
        this.pos.setPos(row, column);
    }

//    protected BufferedImage createImageIcon(String path,
//                                            String description) {
//        if (imgURL != null) {
//            return new BufferedImage(imgURL, description);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//        java.net.URL imgURL = getClass().getResource(path);
//    }

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

