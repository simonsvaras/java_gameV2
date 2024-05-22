package mvc.game.view;

import mvc.game.controller.C_GamePanel;

import java.awt.*;

public class TitleScreenRenderer extends Utils {
    private static final Color BACKGROUND_COLOR = new Color(70, 120, 80);
    private static final Color SHADOW_COLOR = Color.BLACK;
    private static final Color MAIN_TEXT_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 60);
    private static final Font MENU_FONT = new Font("Arial", Font.BOLD, 30);

    private final C_GamePanel gamePanel;
    public int commandNum = 0;

    public TitleScreenRenderer(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void drawTitleScreen(Graphics2D g2) {
        drawBackground(g2);
        drawTitle(g2);
        drawPlayerImage(g2);
        drawMenu(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, C_GamePanel.SCREEN_WIDTH, C_GamePanel.SCREEN_HEIGHT);
    }

    private void drawTitle(Graphics2D g2) {
        g2.setFont(TITLE_FONT);
        String text = "Blue boy adventure";
        int x = getXForCenteredText(text, g2);
        int y = C_GamePanel.TILE_SIZE * 3;

        // Draw shadow
        g2.setColor(SHADOW_COLOR);
        g2.drawString(text, x + 5, y + 5);

        // Draw main text
        g2.setColor(MAIN_TEXT_COLOR);
        g2.drawString(text, x, y);
    }

    private void drawPlayerImage(Graphics2D g2) {
        int x = C_GamePanel.SCREEN_WIDTH / 2 - (C_GamePanel.TILE_SIZE * 2) / 2;
        int y = C_GamePanel.TILE_SIZE * 5;
        g2.drawImage(gamePanel.player.downImage1, x, y, C_GamePanel.TILE_SIZE * 2, C_GamePanel.TILE_SIZE * 2, null);
    }

    private void drawMenu(Graphics2D g2) {
        g2.setFont(MENU_FONT);

        String[] menuItems = {"NEW GAME", "LOAD GAME", "QUIT"};
        int y = C_GamePanel.TILE_SIZE * 9;

        for (int i = 0; i < menuItems.length; i++) {
            String text = menuItems[i];
            int x = getXForCenteredText(text, g2);
            g2.drawString(text, x, y);

            if (commandNum == i) {
                g2.drawString(">", x - C_GamePanel.TILE_SIZE, y);
            }

            y += C_GamePanel.TILE_SIZE;
        }
    }
}