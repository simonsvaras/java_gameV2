package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_NPCs;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPCRenderer {
    C_GamePanel gamePanel;

    public NPCRenderer(C_GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void render(Graphics2D g2){
        for (M_NPCs NPC : gamePanel.getNpcs()) {
            int tempScreenX = NPC.getWorldX() - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int tempScreenY = NPC.getWorldY() - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            BufferedImage image = NPC.getCurrentFrame(NPC.getSpriteNum());
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
    }
}
