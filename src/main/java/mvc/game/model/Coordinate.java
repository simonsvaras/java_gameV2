package mvc.game.model;

/**
 * Represents a coordinate in the 2D space.
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructs a coordinate with the specified x and y values.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters for x and y
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adds another coordinate to this coordinate.
     *
     * @param other The other coordinate.
     */
    public void add(Coordinate other) {
        this.x += other.getX();
        this.y += other.getY();
    }

    /**
     * Subtracts another coordinate from this coordinate.
     *
     * @param other The other coordinate.
     */
    public void subtract(Coordinate other) {
        this.x -= other.getX();
        this.y -= other.getY();
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
