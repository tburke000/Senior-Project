/**
 * Represents a tile, used in the game Settlers of Catan
 *
 * @author burke
 */
public class Tile {
    private String type;

    public Tile (String type) {

    }

    /**
     * Gives a resource to a player
     * @return
     */
    private Resource giveResource () {
        String resourceType;
        switch (this.type){
            case "Hill":
                resourceType = "Brick";
                break;
            case "Pasture":
                resourceType = "Wool";
                break;
            case "Mountain":
                resourceType = "Ore";
                break;
            case "Field":
                resourceType = "Grain";
                break;
            case "Forest":
                resourceType = "Lumber";
                break;
            default:
                return null;
        }

        return new Resource();
    }

}
