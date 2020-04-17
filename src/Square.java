/**
 * Represents a square on a checkerboard
 * @author burke
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

    /**
     * Sets the position of the Square to the given row and column
     * @param row an int representing the horizontal position of the square
     * @param column an int representing the vertical position of the square
     */
    public void setPos (int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the location of the square in pixels.
     * @return
     */
    public int[] getPixel () {
        int[] pixelPos = {420 + row * 80, column * 80};
        return pixelPos;
    }
}
