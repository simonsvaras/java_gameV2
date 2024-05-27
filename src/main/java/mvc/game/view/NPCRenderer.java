package mvc.game.view;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.NPCs;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPCRenderer {
    GamePanel gamePanel;

    public NPCRenderer(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void render(Graphics2D g2){
        for (NPCs NPC : gamePanel.getNpcs()) {
            int tempScreenX = NPC.getWorldX() - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int tempScreenY = NPC.getWorldY() - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            BufferedImage image = NPC.getCurrentFrame(NPC.getSpriteNum());
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
    }
}
