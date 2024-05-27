package mvc.game.view;

import mvc.game.controller.GamePanel;

import java.awt.*;

/**
 * Handles the rendering of the title screen.
 */
public class TitleScreenRenderer extends Utils {
    private static final Color BACKGROUND_COLOR = new Color(70, 120, 80);
    private static final Color SHADOW_COLOR = Color.BLACK;
    private static final Color MAIN_TEXT_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 60);
    private static final Font MENU_FONT = new Font("Arial", Font.BOLD, 30);

    private final GamePanel gamePanel;
    /**
     * The current selected menu command.
     */
    public int commandNum = 0;

    /**
     * Constructs a new TitleScreenRenderer with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public TitleScreenRenderer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Draws the entire title screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void drawTitleScreen(Graphics2D g2) {
        drawBackground(g2);
        drawTitle(g2);
        drawPlayerImage(g2);
        drawMenu(g2);
    }

    /**
     * Draws the background of the title screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    private void drawBackground(Graphics2D g2) {
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
    }

    /**
     * Draws the title text of the game.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    private void drawTitle(Graphics2D g2) {
        g2.setFont(TITLE_FONT);
        String text = "Blue Boy Adventure";
        int x = getXForCenteredText(text, g2);
        int y = GamePanel.TILE_SIZE * 3;

        // Draw shadow
        g2.setColor(SHADOW_COLOR);
        g2.drawString(text, x + 5, y + 5);

        // Draw main text
        g2.setColor(MAIN_TEXT_COLOR);
        g2.drawString(text, x, y);
    }

    /**
     * Draws the player image on the title screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    private void drawPlayerImage(Graphics2D g2) {
        int x = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE * 2) / 2;
        int y = GamePanel.TILE_SIZE * 5;
        g2.drawImage(gamePanel.getPlayer().getCurrentFrame(0), x, y, GamePanel.TILE_SIZE * 2, GamePanel.TILE_SIZE * 2, null);
    }

    /**
     * Draws the menu options on the title screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    private void drawMenu(Graphics2D g2) {
        g2.setFont(MENU_FONT);

        String[] menuItems = {"NEW GAME", "LOAD GAME", "QUIT"};
        int y = GamePanel.TILE_SIZE * 9;

        for (int i = 0; i < menuItems.length; i++) {
            String text = menuItems[i];
            int x = getXForCenteredText(text, g2);
            g2.drawString(text, x, y);

            // Draw the selection indicator (">") if this menu item is selected
            if (commandNum == i) {
                g2.drawString(">", x - GamePanel.TILE_SIZE, y);
            }

            y += GamePanel.TILE_SIZE;
        }
    }
}
