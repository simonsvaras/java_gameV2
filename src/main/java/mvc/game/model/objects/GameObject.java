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

    public GameObject(C_GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        this.image = setup(imagePath, gamePanel.tileSize, gamePanel.tileSize);
    }


    // Implementace z Interactable
    @Override
    public abstract void interact(M_Player player);
    @Override
    public abstract void pickUp(M_Player player);
}