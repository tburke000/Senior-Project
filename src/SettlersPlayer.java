import java.util.LinkedList;

/**
 * Represents a player playing Settlers of Catan
 *
 * @author burke
 */
public class SettlersPlayer {
    private LinkedList<Resource> resources;
    private LinkedList<DevCard> devCards;
    int nVictoryPoints;
    private boolean player;

    public SettlersPlayer (boolean player) {
        this.resources = new LinkedList<>();
        this.devCards = new LinkedList<>();
        this.nVictoryPoints = 0;
        this.player = player;
    }

    /**
     * Plays the given card.
     * @param card
     */
    public void playCard (DevCard card) {
        switch (card.getType()) {

        }
    }

}
