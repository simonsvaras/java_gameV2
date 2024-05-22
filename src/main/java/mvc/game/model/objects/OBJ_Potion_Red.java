package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.M_Player;

import java.awt.*;

public class OBJ_Potion_Red extends GameObject{
    public OBJ_Potion_Red(C_GamePanel gamePanel, String imagePath) {
        super(gamePanel, imagePath);

        name = "Red Potion";
        healAmount = 5;
        description = "[Red Potion]\nHeals your life by " + healAmount + ".";
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

    @Override
    public void interact(M_Player player) {
        player.heal(healAmount);
        gamePanel.playSE(2);
        gamePanel.removeEntity(this);  // Předpokládáme, že existuje metoda pro odstranění objektu
    }

    @Override
    public void pickUp(M_Player player) {
        player.getInventory().addItem(this);
    }
}
