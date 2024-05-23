package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.tile.Tile;
import mvc.game.model.tile.TileManager;

import java.awt.*;

public class TileRenderer {
    private final C_GamePanel gamePanel;
    private final TileManager tileManager;

    public TileRenderer(C_GamePanel gamePanel, TileManager tileManager) {
        this.gamePanel = gamePanel;
        this.tileManager = tileManager;
    }

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

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                int tileNum = mapTileNum[col][row];

                int worldX = col * tileSize;
                int worldY = row * tileSize;
                int screenX = worldX - playerWorldX + playerScreenX;
                int screenY = worldY - playerWorldY + playerScreenY;

                if (isTileVisible(worldX, worldY, tileSize, playerWorldX, playerWorldY, playerScreenX, playerScreenY)) {
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
                }
            }
        }
    }

    private boolean isTileVisible(int worldX, int worldY, int tileSize, int playerWorldX, int playerWorldY, int playerScreenX, int playerScreenY) {
        return worldX + tileSize > playerWorldX - playerScreenX &&
                worldX - tileSize < playerWorldX + playerScreenX &&
                worldY + tileSize > playerWorldY - playerScreenY &&
                worldY - tileSize < playerWorldY + playerScreenY;
    }
}
