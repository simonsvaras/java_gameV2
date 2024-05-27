package mvc.game.view;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Handles the rendering of the player.
 */
public class PlayerRenderer {
    private GamePanel gamePanel;
    private Player player;

    /**
     * Constructs a new PlayerRenderer with the specified game panel and player.
     *
     * @param gamePanel The game panel.
     * @param player The player to be rendered.
     */
    public PlayerRenderer(GamePanel gamePanel, Player player) {
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
                    tempScreenY -= GamePanel.TILE_SIZE;
                    break;
                case LEFT:
                    tempScreenX -= GamePanel.TILE_SIZE;
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
