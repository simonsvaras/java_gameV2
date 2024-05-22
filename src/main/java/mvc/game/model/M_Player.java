package mvc.game.model;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.invenory.Inventory;

import java.awt.*;

public class M_Player extends M_Entity{
    private Inventory inventory;

    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;

    public M_Player(C_GamePanel gamePanel) {
        super(gamePanel);
        this.inventory = new Inventory();

        // making exact middle point of screen to be sure our player will be always in the middle
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void setAction() {

    }

    @Override
    public void damageReaction() {

    }

    @Override
    public void checkDrop() {

    }
}