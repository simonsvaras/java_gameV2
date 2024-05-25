package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Handles the rendering of the player.
 */
public class PlayerRenderer {
    private C_GamePanel gamePanel;
    private M_Player player;

    /**
     * Constructs a new PlayerRenderer with the specified game panel and player.
     *
     * @param gamePanel The game panel.
     * @param player The player to be rendered.
     */
    public PlayerRenderer(C_GamePanel gamePanel, M_Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    /**
     * Renders the player on the screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void render(Graphics2D g2) {
        int tempScreenX = player.getScreenX();
        int tempScreenY = player.getScreenY();

        // Adjust position if the player is attacking
        if (player.getIsAttacking()) {
            switch (player.getDirection()) {
                case UP:
                    tempScreenY -= C_GamePanel.TILE_SIZE;
                    break;
                case LEFT:
                    tempScreenX -= C_GamePanel.TILE_SIZE;
                    break;
                // No adjustment needed for DOWN and RIGHT directions
                default:
                    break;
            }
        }

        // Get the current frame of the player's sprite
        BufferedImage image = player.getCurrentFrame(player.getSpriteNum());
        // Draw the player image at the calculated position
        g2.drawImage(image, tempScreenX, tempScreenY, null);
    }
}
