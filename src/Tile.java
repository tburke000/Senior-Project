import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Represents a tile, used in the game Settlers of Catan
 *
 * @author burke
 */
public class Tile {
    private String type;
    public BufferedImage image = new BufferedImage(222, 256, BufferedImage.TYPE_INT_RGB);
    private String resourceType;

    public Tile (String type) {
        this.type = type;

        try {
            switch (this.type){
                case "Hill":
                    this.resourceType = "Brick";
                    this.image = ImageIO.read(new File("src/hills.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Pasture":
                    this.resourceType = "Wool";
                    this.image = ImageIO.read(new File("src/pasture.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Mountain":
                    this.resourceType = "Ore";
                    this.image = ImageIO.read(new File("src/mountains.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Field":
                    this.resourceType = "Grain";
                    this.image = ImageIO.read(new File("src/fields.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                case "Forest":
                    this.resourceType = "Lumber";
                    this.image = ImageIO.read(new File("src/forests.png"));
                    System.out.println("Successfully found image for tile");
                    break;
                default:
                    this.image = ImageIO.read(new File("src/desert.png"));
                    System.out.println("Successfully found image for tile");
            }
        } catch (Exception e) {
            System.out.println(e + ": Error finding image");
        }

    }

    /**
     * Gives a resource to a player
     * @return
     */
    private Resource giveResource () {

        return new Resource(resourceType);

    }

    /**
     * Returns the image representation of a hexagonal tile for Settlers
     * @return
     */
    public BufferedImage getImage () {
        return image;
    }

}
