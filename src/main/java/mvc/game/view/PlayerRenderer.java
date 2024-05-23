package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.M_Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerRenderer {
    C_GamePanel gamePanel;
    M_Player player;

    public PlayerRenderer(C_GamePanel gamePanel, M_Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    public void render(Graphics2D g2) {
        int tempScreenX = player.screenX;
        int tempScreenY = player.screenY;

        if (player.isAttacking) {
            switch (player.direction) {
                case UP:
                    tempScreenY -= C_GamePanel.TILE_SIZE;
                    break;
                case LEFT:
                    tempScreenX -= C_GamePanel.TILE_SIZE;
                    break;
            }
        }

        BufferedImage image = player.getCurrentFrame(player.spriteNum);
        g2.drawImage(image, tempScreenX, tempScreenY, null);

    }

    /*
    public void render(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = player.screenX;
        int tempScreenY = player.screenY;

        switch (player.direction) {
            case "up":
                if (!player.isAttacking) {
                    if (player.spriteNum == 1) image = player.upImage1;
                    if (player.spriteNum == 2) image = player.upImage1;
                }
                if (player.isAttacking) {
                    tempScreenY = player.screenY - C_GamePanel.TILE_SIZE;
                    if (player.spriteNum == 1) image = player.attackUpImages1;
                    if (player.spriteNum == 2) image = player.attackUpImages2;
                }
                break;

            case "down":
                if (!player.isAttacking) {
                    if (player.spriteNum == 1) image = player.downImage1;
                    if (player.spriteNum == 2) image = player.downImage2;
                }
                if (player.isAttacking) {
                    if (player.spriteNum == 1) image = player.attackDownImages1;
                    if (player.spriteNum == 2) image = player.attackDownImages2;
                }
                break;

            case "left":
                if (!player.isAttacking) {
                    if (player.spriteNum == 1) image = player.leftImage1;
                    if (player.spriteNum == 2) image = player.leftImage2;
                }
                if (player.isAttacking) {
                    tempScreenX = player.screenX - C_GamePanel.TILE_SIZE;
                    if (player.spriteNum == 1) image = player.attackLeftImages1;
                    if (player.spriteNum == 2) image = player.attackLeftImages2;
                }
                break;

            case "right":
                if (!player.isAttacking) {
                    if (player.spriteNum == 1) image = player.rightImage1;
                    if (player.spriteNum == 2) image = player.rightImage2;
                }
                if (player.isAttacking) {
                    if (player.spriteNum == 1) image = player.attackRightImages1;
                    if (player.spriteNum == 2) image = player.attackRightImages2;
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
    }

     */
}
