/**
 * A class representing a piece in a board game. Used for Chess, Checkers, and maybe Battleship
 */
public class Piece {
    // Boolean representing who owns the piece. True for Player One, False for Player Two.
    private boolean owner;
    // String representing the type of piece. Will be used in check methods depending on the game.
    private String type;

    public Piece (boolean owner, String type) {
        this.owner = owner;
        this.type = type;
    }

}
