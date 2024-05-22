package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.M_Player;

import java.awt.*;

public class OBJ_Axe extends GameObject{
    public OBJ_Axe(C_GamePanel gamePanel, String imagePath) {
        super(gamePanel, imagePath);
        name = "Axe";
        type = TYPE_AXE;
        downImage1 = setup("/objects/axe", gamePanel.tileSize, gamePanel.tileSize);

        attackValue = 2;

        attackArea.width = 30;
        attackArea.height = 30;

        description = "[" + name + "]\nCan cut a trees";
    }

    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {
        player.getInventory().addItem(this);
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
