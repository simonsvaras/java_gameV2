package mvc.game.model.tile;

import mvc.game.controller.GamePanel;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Manages the tiles in the game, including loading and rendering.
 */
public class TileManager {
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    /**
     * Constructs a new TileManager with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    /**
     * Loads the images for the tiles.
     */
    public void getTileImage() {
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
    }

    /**
     * Sets up a tile with the specified parameters.
     *
     * @param index The index of the tile.
     * @param imageName The name of the image file.
     * @param collision Whether the tile has collision.
     */
    private void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, GamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the map from a file.
     *
     * @param filePath The path to the map file.
     */
    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW) {
                String line = br.readLine();

                while (col < GamePanel.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == GamePanel.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the array of tiles.
     *
     * @return The array of tiles.
     */
    public Tile[] getTiles() {
        return tile;
    }

    /**
     * Returns the map tile numbers.
     *
     * @return The map tile numbers.
     */
    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    /**
     * Returns the tile number at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The tile number.
     */
    public int getTileAt(int x, int y) {
        return mapTileNum[x][y];
    }

    /**
     * Returns the tile at the specified tile number.
     *
     * @param tileNum The tile number.
     * @return The tile.
     */
    public Tile getTile(int tileNum) {
        return tile[tileNum];
    }

    /**
     * Checks if a tile at the specified coordinates is collidable.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return true if the tile is collidable, false otherwise.
     */
    public boolean isTileCollidable(int x, int y) {
        if (x < 0 || x >= GamePanel.MAX_WORLD_COL || y < 0 || y >= GamePanel.MAX_WORLD_ROW) {
            return true;
        }
        int tileI = getTileAt(x, y);
        return getTile(tileI).hasCollision();
    }
}
