package mvc.game.model.entity;

import mvc.game.controller.GamePanel;
import mvc.game.model.Coordinate;
import mvc.game.model.Direction;
import mvc.game.model.Sprites;

import java.awt.image.BufferedImage;

/**
 * Represents a live object in the game, such as a player or NPC.
 */
public class LiveObjects extends Entity {

    protected Sprites sprite = new Sprites();

    // Screen position => doesn't change
    /**
     * The x-coordinate of the object on the screen.
     */
    protected final int screenX;

    /**
     * The y-coordinate of the object on the screen.
     */
    protected final int screenY;

    // CHARACTER ATTRIBUTES
    /**
     * The direction the object is facing.
     */
    protected Direction direction;

    /**
     * The speed of the object.
     */
    protected int speed;

    /**
     * The maximum life of the object.
     */
    protected int maxLife;

    /**
     * The current life of the object.
     */
    protected int life;

    /**
     * The strength attribute of the object.
     */
    protected int strength;

    /**
     * The dexterity attribute of the object.
     */
    protected int dexterity;

    /**
     * The defense attribute of the object.
     */
    protected int defense;

    /**
     * The attack attribute of the object.
     */
    protected int attack;

    /**
     * The level of the object.
     */
    protected int level;

    /**
     * The current experience points of the object.
     */
    protected int exp;

    /**
     * The experience points required for the next level.
     */
    protected int nextLevelExp;

    /**
     * The amount of coins the object has.
     */
    protected int coin;

    /**
     * Indicates if the object is attacking.
     */
    protected boolean isAttacking;

    /**
     * Indicates if the object is alive.
     */
    protected boolean alive;

    /**
     * Indicates if the object is dying.
     */
    protected boolean dying;


    /**
     * The index of the current dialogue.
     */
    protected int dialogueIndex;

    /**
     * Constructs a new live object with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public LiveObjects(GamePanel gamePanel) {
        super(gamePanel, GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2),
                GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2));
        // Making exact middle point of screen to ensure the player is always in the middle
        screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
        screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);

        isAttacking = false;
        alive = true;
        dying = false;
        dialogueIndex = 0;
    }

    /**
     * Loads the player's movement sprites.
     */
    @Override
    public void loadPlayerSprites() {
    }

    /**
     * Moves the object up.
     */
    public void moveUp() {
        direction = Direction.UP;
        position.setY(position.getY() - speed);
    }

    /**
     * Moves the object down.
     */
    public void moveDown() {
        direction = Direction.DOWN;
        position.setY(position.getY() + speed);
    }

    /**
     * Moves the object left.
     */
    public void moveLeft() {
        direction = Direction.LEFT;
        position.setX(position.getX() - speed);
    }

    /**
     * Moves the object right.
     */
    public void moveRight() {
        direction = Direction.RIGHT;
        position.setX(position.getX() + speed);
    }

    /**
     * Gets the current frame of the object based on its action and direction.
     *
     * @param frameIndex The index of the frame to retrieve.
     * @return The current frame image.
     */
    public BufferedImage getCurrentFrame(int frameIndex) {
        String actionKey = direction.toString().toLowerCase();
        if (isAttacking) {
            actionKey = "attack_" + actionKey;
        }
        return sprite.getFrame(actionKey, frameIndex);
    }

    /**
     * Updates the movement frame of the object for animation.
     */
    public void updateMovementFrame() {
        spriteCounter++;
        if (spriteCounter > 15) {
            spriteCounter = 0;
            // Toggle between sprite frames for animation
            spriteNum = (spriteNum + 1) % 2;
        }
    }

    /**
     * Gets the speed of the object.
     *
     * @return The speed of the object.
     */
    public int getSpeed() {
        return speed;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean getIsAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
