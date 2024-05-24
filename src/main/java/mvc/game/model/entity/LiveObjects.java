package mvc.game.model.entity;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.Sprites;

import java.awt.image.BufferedImage;

/**
 * Represents a live object in the game, such as a player or NPC.
 */
public class LiveObjects extends M_Entity {

    protected Sprites sprite = new Sprites();

    // Screen position => doesn't change
    /**
     * The x-coordinate of the object on the screen.
     */
    public final int screenX;

    /**
     * The y-coordinate of the object on the screen.
     */
    public final int screenY;

    // CHARACTER ATTRIBUTES
    /**
     * The direction the object is facing.
     */
    public Direction direction;

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
    public boolean isAttacking;

    /**
     * Indicates if the object is alive.
     */
    protected boolean alive;

    /**
     * Indicates if the object is dying.
     */
    protected boolean dying;

    /**
     * The dialogues for the object.
     */
    public String[] dialogues = new String[20];

    /**
     * The index of the current dialogue.
     */
    protected int dialogueIndex;

    /**
     * Constructs a new live object with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public LiveObjects(C_GamePanel gamePanel) {
        super(gamePanel);
        // Making exact middle point of screen to ensure the player is always in the middle
        screenX = C_GamePanel.SCREEN_WIDTH / 2 - (C_GamePanel.TILE_SIZE / 2);
        screenY = C_GamePanel.SCREEN_HEIGHT / 2 - (C_GamePanel.TILE_SIZE / 2);

        isAttacking = false;
        alive = true;
        dying = false;
        dialogueIndex = 0;
    }

    /**
     * Moves the object up.
     */
    public void moveUp() {
        direction = Direction.UP;
        worldY -= speed;
    }

    /**
     * Moves the object down.
     */
    public void moveDown() {
        direction = Direction.DOWN;
        worldY += speed;
    }

    /**
     * Moves the object left.
     */
    public void moveLeft() {
        direction = Direction.LEFT;
        worldX -= speed;
    }

    /**
     * Moves the object right.
     */
    public void moveRight() {
        direction = Direction.RIGHT;
        worldX += speed;
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
            if (spriteNum == 0) {
                spriteNum = 1;
            } else {
                spriteNum = 0;
            }
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
}
