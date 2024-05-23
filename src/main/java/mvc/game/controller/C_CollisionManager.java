package mvc.game.controller;

import mvc.game.model.Direction;
import mvc.game.model.LiveObjects;
import mvc.game.model.M_Entity;
import mvc.game.model.M_Player;
import mvc.game.model.tile.TileManager;
import org.game.entity.Entity;
import org.game.main.GamePanel;

/**
 * Manages collision detection within the game, particularly between the player and the map tiles.
 * This class checks if an entity, such as a player, collides with any impassable tiles on the map
 * based on the direction of movement.
 */
public class C_CollisionManager {
    // Reference to the game panel where the game's main mechanics are controlled.
    C_GamePanel gamePanel;

    /**
     * Constructor for the collision manager.
     * @param gamePanel The main game panel that holds references to other components and game settings.
     */
    public C_CollisionManager(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Checks for collision between a movable entity and the tiles of the game map in a given direction.
     * Calculates the entity's next position based on its speed and direction and checks if any of
     * the four corners of its bounding box collides with impassable tiles.
     *
     * @param entity The entity whose collision is being checked, typically the player.
     * @param direction The direction in which the entity is trying to move.
     * @return true if there is a collision with an impassable tile, otherwise false.
     */
    public boolean checkTileCollision(LiveObjects entity, Direction direction) {
        // Calculate displacement based on the direction
        int dx = 0, dy = 0;
        switch (direction) {
            case UP:
                dy = -entity.getSpeed(); // Moving up reduces the y-coordinate
                break;
            case DOWN:
                dy = entity.getSpeed(); // Moving down increases the y-coordinate
                break;
            case LEFT:
                dx = -entity.getSpeed(); // Moving left reduces the x-coordinate
                break;
            case RIGHT:
                dx = entity.getSpeed(); // Moving right increases the x-coordinate
                break;
        }

        // Calculate the new position of each corner of the entity's bounding box
        int newLeftX = (entity.worldX + dx + entity.solidArea.x) / C_GamePanel.TILE_SIZE;
        int newRightX = (entity.worldX + dx + entity.solidArea.x + entity.solidArea.width) / C_GamePanel.TILE_SIZE;
        int newTopY = (entity.worldY + dy + entity.solidArea.y) / C_GamePanel.TILE_SIZE;
        int newBottomY = (entity.worldY + dy + entity.solidArea.y + entity.solidArea.height) / C_GamePanel.TILE_SIZE;

        // Check each corner for collision with impassable tiles
        return gamePanel.getTileManager().isTileCollidable(newLeftX, newTopY) ||
                gamePanel.getTileManager().isTileCollidable(newRightX, newTopY) ||
                gamePanel.getTileManager().isTileCollidable(newLeftX, newBottomY) ||
                gamePanel.getTileManager().isTileCollidable(newRightX, newBottomY);
    }

}

