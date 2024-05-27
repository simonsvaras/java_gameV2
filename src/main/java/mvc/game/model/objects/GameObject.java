package mvc.game.model.objects;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.Entity;
import mvc.game.model.entity.Player;

import java.awt.image.BufferedImage;

/**
 * Represents a game object that can be interacted with in the game.
 */
public abstract class GameObject extends Entity implements Interactable {
    
    /**
     * The description of the game object.
     */
    protected String description;

    /**
     * The image representing the game object.
     */
    protected BufferedImage image;


    // ITEM ATTRIBUTES

    /**
     * The value of the game object.
     */
    protected int value;

    /**
     * Indicates if the game object is stackable.
     */
    protected boolean stackable;

    /**
     * The amount of the game object.
     */
    protected int amount;

    /**
     * Constructs a new game object with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public GameObject(GamePanel gamePanel, int worldX, int worldY) {
        super(gamePanel, worldX, worldY);
    }


    /**
     * Interacts with the player.
     *
     * @param player The player to interact with.
     */
    @Override
    public abstract void interact(Player player);

    /**
     * Picks up the game object by the player.
     *
     * @param player The player picking up the object.
     */
    @Override
    public abstract void pickUp(Player player);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }
}
