package mvc.game.controller;

import mvc.game.model.M_Entity;
import mvc.game.model.M_Player;
import mvc.game.model.tile.TileManager;
import org.game.entity.Entity;
import org.game.main.GamePanel;

public class C_CollisionManager {
    C_GamePanel gamePanel;
    public C_CollisionManager(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(M_Player entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / C_GamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / C_GamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / C_GamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / C_GamePanel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / C_GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().tile[tileNum1].collision || gamePanel.getTileManager().tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / C_GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().tile[tileNum1].collision || gamePanel.getTileManager().tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / C_GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().tile[tileNum1].collision || gamePanel.getTileManager().tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / C_GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().tile[tileNum1].collision || gamePanel.getTileManager().tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
