import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

/**
 * Class representing the board game Settlers of Catan, playable using the Knight's Party Table.
 * @author burke
 */
public class Settlers extends Game {
    private LinkedList<DevCard> deck;
    private LinkedList<Tile> map;


    public Settlers () {
        deck = generateDeck();
        map = generateMap();
    }

    @Override
    public void start() {
        display();
    }

    @Override
    public void display() {
        for (Tile i : map) {

        }
    }

    @Override
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        return false;
    }

    /**
     * Not necessary for Settlers of Catan
     * @param taker The piece being moved to take the takee
     * @param takee The piece being taken
     */
    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }

    /**
     * Generates the deck of Development Cards for Settlers of Catan
     * @return a shuffled deck of cards in a Linked List
     */
    private LinkedList<DevCard> generateDeck () {
        int nCards = 0;
        int nKnight = 0;
        int nVP = 0;
        int nYearOp = 0;
        int nMonopoly = 0;
        int nRoad = 0;
        LinkedList<DevCard> deck = new LinkedList<>();
        DevCard card;
        while (nCards < 25) {
            while (nKnight < 14) {
                card = new DevCard("Knight");
                deck.add(card);
                nCards++;
                nKnight++;
            }

            while (nVP < 5) {
                card = new DevCard("Victory Point");
                deck.add(card);
                nCards++;
                nVP++;
            }

            while (nYearOp < 2) {
                card = new DevCard("Year of Plenty");
                deck.add(card);
                nCards++;
                nYearOp++;
            }

            while (nMonopoly < 2) {
                card = new DevCard("Monopoly");
                deck.add(card);
                nCards++;
                nYearOp++;
            }

            while (nRoad < 2) {
                card = new DevCard("Road Building");
                deck.add(card);
                nCards++;
                nRoad++;
            }
        }

        Collections.shuffle(deck);

        return deck;
    }

    /**
     * Generates tiles for the map.
     * @return Hex tiles for the map in the form of a Linked List.
     */
    private LinkedList<Tile> generateMap () {
        LinkedList<Tile> tiles = new LinkedList<>();
        int nHexes = 0;
        int nForest = 0;
        int nPasture = 0;
        int nFields = 0;
        int nHills = 0;
        int nMountains = 0;

        while (nHexes < 19) {
            while ((nForest < 4) && (nPasture < 4) && (nFields < 4)) {
                tiles.add(new Tile("Forest"));
                tiles.add(new Tile("Pasture"));
                tiles.add(new Tile("Field"));

                nForest++;
                nPasture++;
                nFields++;
                nHexes++;
            }

            while ((nHills < 3) && (nMountains < 3)) {
                tiles.add(new Tile("Hill"));
                tiles.add(new Tile("Mountain"));

                nHills++;
                nMountains++;
                nHexes++;
            }

            tiles.add(new Tile("Desert"));
        }
        Collections.shuffle(tiles);

        return tiles;

    }

}
