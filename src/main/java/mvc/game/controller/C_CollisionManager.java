package mvc.game.controller;

import mvc.game.model.Direction;
import mvc.game.model.entity.LiveObjects;
import mvc.game.model.entity.M_NPCs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Manages collision detection within the game, particularly between the player and the map tiles, player and NPCs, player and interactiveTile, NPCs and NPCs.
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
        return entity.collisionOn =
                gamePanel.getTileManager().isTileCollidable(newLeftX, newTopY) ||
                gamePanel.getTileManager().isTileCollidable(newRightX, newTopY) ||
                gamePanel.getTileManager().isTileCollidable(newLeftX, newBottomY) ||
                gamePanel.getTileManager().isTileCollidable(newRightX, newBottomY);
    }

    /*
    public boolean checkEntity(LiveObjects entity, ArrayList<M_NPCs> entities) {
        for (LiveObjects target : entities) {
            if (target != null && target != entity) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the target's solid area position
                target.solidArea.x = target.worldX + target.solidArea.x;
                target.solidArea.y = target.worldY + target.solidArea.y;

                // Adjust entity's solid area position based on movement
                moveSolidArea(entity);



                // Check if these two rectangles are colliding
                if (entity.solidArea.intersects(target.solidArea)) {
                    entity.collisionOn = true;
                    return true;
                }

                // Reset solid area positions
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.solidArea.x = target.solidAreaDefaultX;
                target.solidArea.y = target.solidAreaDefaultY;
            }
        }
        return false;
         }

     */

    /**
     * Checks for collisions between the given entity and a list of other entities.
     *
     * @param entity   The entity to check for collisions.
     * @param entities The list of entities to check against.
     * @return true if a collision is detected, false otherwise.
     */
    public boolean checkEntity(LiveObjects entity, ArrayList<M_NPCs> entities) {
        for (LiveObjects target : entities) {
            if (target != null && target != entity) {
                // Move the solid area based on the entity's direction and speed
                moveSolidArea(entity);

                // Create a copy of the solid area of the current entity and move it to its current position
                Rectangle thisArea = new Rectangle(entity.solidArea);
                thisArea.setLocation(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y);

                // Create a copy of the solid area of the other entity and move it to its current position
                Rectangle otherArea = new Rectangle(target.solidArea);
                otherArea.setLocation(target.worldX + target.solidArea.x, target.worldY + target.solidArea.y);

                // Check for collision between the two areas
                if (thisArea.intersects(otherArea)) {
                    entity.collisionOn = true;
                    return true;  // If a collision is detected, the method returns true
                }

                // Reset the solid area positions
                resetSolidAreaPosition(entity);
                resetSolidAreaPosition(target);
            }
        }
        return false;
    }


    /**
     * Moves the solid area of the entity based on its direction and speed.
     *
     * @param entity The entity whose solid area is to be moved.
     */
    private void moveSolidArea(LiveObjects entity) {
        switch (entity.direction) {
            case UP:
                entity.solidArea.y -= entity.getSpeed();
                break;
            case DOWN:
                entity.solidArea.y += entity.getSpeed();
                break;
            case LEFT:
                entity.solidArea.x -= entity.getSpeed();
                break;
            case RIGHT:
                entity.solidArea.x += entity.getSpeed();
                break;
        }
    }

    /**
     * Resets the solid area position of the entity to its default values.
     *
     * @param entity The entity whose solid area position is to be reset.
     */
    private void resetSolidAreaPosition(LiveObjects entity) {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
    }
}

