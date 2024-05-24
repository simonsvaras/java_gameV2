package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.tile.Tile;
import mvc.game.model.tile.TileManager;

import java.awt.*;

/**
 * Handles the rendering of the tiles in the game.
 */
public class TileRenderer {
    private final C_GamePanel gamePanel;
    private final TileManager tileManager;

    /**
     * Constructs a new TileRenderer with the specified game panel and tile manager.
     *
     * @param gamePanel The game panel.
     * @param tileManager The tile manager.
     */
    public TileRenderer(C_GamePanel gamePanel, TileManager tileManager) {
        this.gamePanel = gamePanel;
        this.tileManager = tileManager;
    }

    /**
     * Renders the tiles on the screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void render(Graphics2D g2) {
        Tile[] tiles = tileManager.getTiles();
        int[][] mapTileNum = tileManager.getMapTileNum();

        int maxCols = C_GamePanel.MAX_WORLD_COL;
        int maxRows = C_GamePanel.MAX_WORLD_ROW;
        int tileSize = C_GamePanel.TILE_SIZE;

        int playerWorldX = gamePanel.player.worldX;
        int playerWorldY = gamePanel.player.worldY;
        int playerScreenX = gamePanel.getPlayer().screenX;
        int playerScreenY = gamePanel.getPlayer().screenY;

        // Loop through all the rows and columns of the world
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                int tileNum = mapTileNum[col][row];

                int worldX = col * tileSize;
                int worldY = row * tileSize;
                int screenX = worldX - playerWorldX + playerScreenX;
                int screenY = worldY - playerWorldY + playerScreenY;

                // Only render the tile if it is within the visible area of the screen
                if (isTileVisible(worldX, worldY, tileSize, playerWorldX, playerWorldY, playerScreenX, playerScreenY)) {
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
                }
            }
        }
    }

    /**
     * Determines if a tile is visible on the screen.
     *
     * @param worldX The x-coordinate of the tile in the world.
     * @param worldY The y-coordinate of the tile in the world.
     * @param tileSize The size of the tile.
     * @param playerWorldX The x-coordinate of the player in the world.
     * @param playerWorldY The y-coordinate of the player in the world.
     * @param playerScreenX The x-coordinate of the player on the screen.
     * @param playerScreenY The y-coordinate of the player on the screen.
     * @return true if the tile is visible on the screen, false otherwise.
     */
    private boolean isTileVisible(int worldX, int worldY, int tileSize, int playerWorldX, int playerWorldY, int playerScreenX, int playerScreenY) {
        return worldX + tileSize > playerWorldX - playerScreenX &&
                worldX - tileSize < playerWorldX + playerScreenX &&
                worldY + tileSize > playerWorldY - playerScreenY &&
                worldY - tileSize < playerWorldY + playerScreenY;
    }
}
