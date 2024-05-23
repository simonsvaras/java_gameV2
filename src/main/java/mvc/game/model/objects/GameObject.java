package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.M_Entity;
import mvc.game.model.M_Player;

import java.awt.image.BufferedImage;

public abstract class GameObject extends M_Entity implements Interactable {
    protected String name;
    protected String description;
    protected BufferedImage image;

    protected int healAmount;

    // ITEM ATTRIBUTES
    public int attackValue;
    protected int value;
    public int defenseValue;
    protected boolean stackable;
    protected int amount;

    public GameObject(C_GamePanel gamePanel) {
        super(gamePanel);

    }


    // Implementace z Interactable
    @Override
    public abstract void interact(M_Player player);
    @Override
    public abstract void pickUp(M_Player player);
}