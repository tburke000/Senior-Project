/**
 * Represents a square on a checkerboard
 */
public class Square {
    public int row;
    public int column;
    public Square (int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String toString () {
        return (row) + ", " + (column);
    }
}
