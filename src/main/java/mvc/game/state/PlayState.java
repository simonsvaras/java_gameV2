package mvc.game.state;

import mvc.game.controller.C_CollisionManager;
import mvc.game.controller.C_GamePanel;
import mvc.game.controller.C_KeyHandler;
import mvc.game.model.*;
import mvc.game.model.entity.LiveObjects;
import mvc.game.model.entity.M_NPCs;
import mvc.game.model.entity.M_Player;
import mvc.game.view.NPCRenderer;
import mvc.game.view.PlayerRenderer;
import mvc.game.view.TileRenderer;

import java.awt.*;
import java.util.Random;

/**
 * The state of the game where the main gameplay occurs.
 */
public class PlayState implements GameState {

    private final C_GamePanel gamePanel;
    private final TileRenderer tileRenderer;
    private final PlayerRenderer playerRenderer;
    private final C_CollisionManager collisionManager;
    private final NPCRenderer npcRenderer;
    private final C_KeyHandler keyHandler;

    private Random random = new Random();

    /**
     * Constructs a new PlayState with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public PlayState(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.keyHandler = gamePanel.getKeyHandler();
        this.tileRenderer = new TileRenderer(gamePanel, gamePanel.getTileManager());
        this.playerRenderer = new PlayerRenderer(gamePanel, (M_Player) gamePanel.getPlayer());
        this.npcRenderer = new NPCRenderer(gamePanel);
        this.collisionManager = new C_CollisionManager(gamePanel);
    }

    /**
     * Updates the game state.
     */
    @Override
    public void update() {
        // Update player's movement
        handleMovement(gamePanel.getPlayer());

        // Update NPC movements
        for (M_NPCs NPC : gamePanel.getNpcs()) {
            NPC.collisionOn = false;
            handleNPCMovement(NPC);
        }
    }

    /**
     * Renders the game state.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    @Override
    public void render(Graphics2D g2) {
        tileRenderer.render(g2);
        playerRenderer.render(g2);
        npcRenderer.render(g2);
    }

    /**
     * Handles the movement logic for an NPC.
     *
     * @param object The NPC to move.
     */
    private void handleNPCMovement(M_NPCs object) {
        Direction previousDirection = object.direction;

        object.actionLockCounter++;
        if (object.actionLockCounter > object.getMaxActionLockCounter()) {
            changeDirectionRandomly(object);
            object.actionLockCounter = 0; // Reset counter after changing direction
        }

        tryMove(object, object.direction);

        changeMovementFrame(object, previousDirection);
    }

    /**
     * Changes the direction of the NPC randomly.
     *
     * @param object The NPC whose direction will change.
     */
    private void changeDirectionRandomly(M_NPCs object) {
        int directionChoice = random.nextInt(4); // Generates a number between 0 and 3

        switch (directionChoice) {
            case 0:
                object.direction = Direction.UP;
                break;
            case 1:
                object.direction = Direction.DOWN;
                break;
            case 2:
                object.direction = Direction.LEFT;
                break;
            case 3:
                object.direction = Direction.RIGHT;
                break;
        }
    }

    /**
     * Handles the movement logic for a player or NPC.
     *
     * @param object The player or NPC to move.
     */
    private void handleMovement(LiveObjects object) {
        object.collisionOn = false;

        boolean hasMoved = false;
        Direction previousDirection = object.direction;

        if (keyHandler.upPressed) {
            object.direction = Direction.UP;
            hasMoved = tryMove(object, object.direction);
        }

        if (keyHandler.downPressed) {
            object.direction = Direction.DOWN;
            hasMoved = tryMove(object, object.direction);
        }

        if (keyHandler.leftPressed) {
            object.direction = Direction.LEFT;
            hasMoved = tryMove(object, object.direction);
        }

        if (keyHandler.rightPressed) {
            object.direction = Direction.RIGHT;
            hasMoved = tryMove(object, object.direction);
        }

        if (hasMoved) {
            changeMovementFrame(object, previousDirection);
        }
    }

    /**
     * Changes the movement frame of an object for animation purposes.
     *
     * @param object The object whose movement frame will change.
     * @param previousDirection The previous direction of the object.
     */
    private void changeMovementFrame(LiveObjects object, Direction previousDirection) {
        if (object.direction != previousDirection) {
            object.resetAnimation(); // Reset animation if direction changes
        }
        object.updateMovementFrame();
    }

    /**
     * Tries to move the object in the specified direction.
     *
     * @param object The object to move.
     * @param direction The direction to move the object.
     * @return true if the object moved, false otherwise.
     */
    private boolean tryMove(LiveObjects object, Direction direction) {
        collisionManager.checkTileCollision(object, direction);

        //collisionManager.checkEntity(object, gamePanel.getNpcs());

        if (!object.collisionOn) {
            switch (direction) {
                case UP:
                    object.moveUp();
                    break;
                case DOWN:
                    object.moveDown();
                    break;
                case LEFT:
                    object.moveLeft();
                    break;
                case RIGHT:
                    object.moveRight();
                    break;
            }

            return true;
        }
        return false;
    }
}
