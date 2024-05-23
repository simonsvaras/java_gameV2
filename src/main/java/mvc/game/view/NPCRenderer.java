package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.M_NPCs;
import mvc.game.model.M_Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPCRenderer {
    C_GamePanel gamePanel;

    public NPCRenderer(C_GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void render(Graphics2D g2){
        for (M_NPCs NPC : gamePanel.getNpcs()) {
            int tempScreenX = NPC.worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
            int tempScreenY = NPC.worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;

            BufferedImage image = NPC.getCurrentFrame(NPC.spriteNum);
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
    }
}
