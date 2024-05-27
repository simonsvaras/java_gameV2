package mvc.game.state;

import mvc.game.controller.CollisionManager;
import mvc.game.controller.GamePanel;
import mvc.game.controller.KeyHandler;
import mvc.game.model.*;
import mvc.game.model.entity.LiveObjects;
import mvc.game.model.entity.NPCs;
import mvc.game.model.entity.Player;
import mvc.game.view.NPCRenderer;
import mvc.game.view.PlayerRenderer;
import mvc.game.view.TileRenderer;

import java.awt.*;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The state of the game where the main gameplay occurs.
 */
public class PlayState implements GameState {
    private static final Logger logger = LogManager.getLogger(GamePanel.class);

    private final GamePanel gamePanel;
    private final TileRenderer tileRenderer;
    private final PlayerRenderer playerRenderer;
    private final CollisionManager collisionManager;
    private final NPCRenderer npcRenderer;
    private final KeyHandler keyHandler;

    private Random random = new Random();

    /**
     * Constructs a new PlayState with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public PlayState(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.keyHandler = gamePanel.getKeyHandler();
        this.tileRenderer = new TileRenderer(gamePanel, gamePanel.getTileManager());
        this.playerRenderer = new PlayerRenderer(gamePanel, (Player) gamePanel.getPlayer());
        this.npcRenderer = new NPCRenderer(gamePanel);
        this.collisionManager = new CollisionManager(gamePanel);
    }

    /**
     * Updates the game state.
     */
    @Override
    public void update() {
        // Update player's movement
        handleMovement(gamePanel.getPlayer());

        // Update NPC movements
        for (NPCs NPC : gamePanel.getNpcs()) {
            NPC.setCollisionOn(false);
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
    private void handleNPCMovement(NPCs object) {
        Direction previousDirection = object.getDirection();

        object.setActionLockCounter(object.getActionLockCounter() + 1);
        if (object.getActionLockCounter() > object.getMaxActionLockCounter()) {
            changeDirectionRandomly(object);
            object.setActionLockCounter(0); // Reset counter after changing direction
        }

        tryMove(object, object.getDirection());

        changeMovementFrame(object, previousDirection);
    }

    /**
     * Changes the direction of the NPC randomly.
     *
     * @param object The NPC whose direction will change.
     */
    private void changeDirectionRandomly(NPCs object) {
        int directionChoice = random.nextInt(4); // Generates a number between 0 and 3

        switch (directionChoice) {
            case 0:
                object.setDirection(Direction.UP);
                break;
            case 1:
                object.setDirection(Direction.DOWN);
                break;
            case 2:
                object.setDirection(Direction.LEFT);
                break;
            case 3:
                object.setDirection(Direction.RIGHT);
                break;
        }
    }

    /**
     * Handles the movement logic for a player or NPC.
     *
     * @param object The player or NPC to move.
     */
    private void handleMovement(LiveObjects object) {
        object.setCollisionOn(false);

        boolean hasMoved = false;
        Direction previousDirection = object.getDirection();

        if (keyHandler.isUpPressed()) {
            object.setDirection(Direction.UP);
            hasMoved = tryMove(object, object.getDirection());
        }

        if (keyHandler.isDownPressed()) {
            object.setDirection(Direction.DOWN);
            hasMoved = tryMove(object, object.getDirection());
        }

        if (keyHandler.isLeftPressed()) {
            object.setDirection(Direction.LEFT);
            hasMoved = tryMove(object, object.getDirection());
        }

        if (keyHandler.isEnterPressed()) {
            object.setDirection(Direction.RIGHT);
            hasMoved = tryMove(object, object.getDirection());
        }

        if (hasMoved) {
            changeMovementFrame(object, previousDirection);
        }
        if(object.getCollisionOn()){
            logger.info("Movement blocked: {} could not move in direction {}", object.getName(), object.getDirection());
        }
    }

    /**
     * Changes the movement frame of an object for animation purposes.
     *
     * @param object The object whose movement frame will change.
     * @param previousDirection The previous direction of the object.
     */
    private void changeMovementFrame(LiveObjects object, Direction previousDirection) {
        if (object.getDirection() != previousDirection) {
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
    public boolean tryMove(LiveObjects object, Direction direction) {
        collisionManager.checkTileCollision(object, direction);

        //CollisionManager.checkEntity(object, gamePanel.getNpcs());

        if (!object.getCollisionOn()) {
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
