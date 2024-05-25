package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Entity;
import mvc.game.model.entity.M_Player;

import java.awt.image.BufferedImage;

/**
 * Represents a game object that can be interacted with in the game.
 */
public abstract class GameObject extends M_Entity implements Interactable {

    /**
     * The name of the game object.
     */
    protected String name;

    /**
     * The description of the game object.
     */
    protected String description;

    /**
     * The image representing the game object.
     */
    protected BufferedImage image;

    /**
     * The amount of healing provided by the game object.
     */
    protected int healAmount;

    // ITEM ATTRIBUTES
    /**
     * The attack value of the game object.
     */
    protected int attackValue;

    /**
     * The value of the game object.
     */
    protected int value;

    /**
     * The defense value of the game object.
     */
    public int defenseValue;

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
    public GameObject(C_GamePanel gamePanel) {
        super(gamePanel);
    }

    /**
     * Interacts with the player.
     *
     * @param player The player to interact with.
     */
    @Override
    public abstract void interact(M_Player player);

    /**
     * Picks up the game object by the player.
     *
     * @param player The player picking up the object.
     */
    @Override
    public abstract void pickUp(M_Player player);

    public int getAttackValue() {
        return attackValue;
    }

}
