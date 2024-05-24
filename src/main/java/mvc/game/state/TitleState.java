package mvc.game.state;

import mvc.game.controller.C_GamePanel;
import mvc.game.view.TitleScreenRenderer;

import java.awt.*;

/**
 * Represents the title screen state of the game.
 */
public class TitleState implements GameState {
    private final C_GamePanel gamePanel;
    private final TitleScreenRenderer titleScreenRenderer;

    /**
     * Constructs a new TitleState with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public TitleState(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.titleScreenRenderer = new TitleScreenRenderer(gamePanel);
    }

    /**
     * Updates the title screen state.
     */
    @Override
    public void update() {
        // Handle navigation on the title screen
        if (gamePanel.getKeyHandler().upPressed) {
            titleScreenRenderer.commandNum--;
            if (titleScreenRenderer.commandNum < 0) {
                titleScreenRenderer.commandNum = 2;
            }
            gamePanel.getKeyHandler().reset();
        }
        if (gamePanel.getKeyHandler().downPressed) {
            titleScreenRenderer.commandNum++;
            if (titleScreenRenderer.commandNum > 2) {
                titleScreenRenderer.commandNum = 0;
            }
            gamePanel.getKeyHandler().reset();
        }
        if (gamePanel.getKeyHandler().enterPressed) {
            performAction();
            gamePanel.getKeyHandler().reset();
        }
    }

    /**
     * Renders the title screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    @Override
    public void render(Graphics2D g2) {
        titleScreenRenderer.drawTitleScreen(g2);
    }

    /**
     * Performs an action based on the selected menu option.
     */
    private void performAction() {
        switch (titleScreenRenderer.commandNum) {
            case 0:
                // Start new game
                System.out.println("Starting new game...");
                gamePanel.setCurrentState(new PlayState(gamePanel));
                break;
            case 1:
                // Load game
                System.out.println("Loading game...");
                break;
            case 2:
                // Quit game
                System.out.println("Quitting game...");
                System.exit(0);
                break;
        }
    }
}
