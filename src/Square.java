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

    /**
     * Returns the position of the square as an int array
     * @return The position of the square as an int array
     */
    public int[] getPos () {
        return new int[]{row, column};
    }

    public void setPos (int row, int column) {
        this.row = row;
        this.column = column;
    }
}
