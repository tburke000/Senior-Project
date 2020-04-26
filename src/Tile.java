
import java.awt.image.BufferedImage;
/**
 * Represents a tile, used in the game Settlers of Catan
 *
 * @author burke
 */
public class Tile extends Piece {
    private String type;
    private String resourceType;

    public Tile (String type) {
        super(0, 0, false, type);
        this.type = type;
        try {
            switch (this.type){
                case "Hill":
                    this.resourceType = "Brick";
                    break;
                case "Pasture":
                    this.resourceType = "Wool";
                    break;
                case "Mountain":
                    this.resourceType = "Ore";
                    break;
                case "Field":
                    this.resourceType = "Grain";
                    break;
                case "Forest":
                    this.resourceType = "Lumber";
                    break;
                default:
            }
        } catch (Exception e) {

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
