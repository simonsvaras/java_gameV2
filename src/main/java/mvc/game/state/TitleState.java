package mvc.game.state;

import mvc.game.controller.GamePanel;
import mvc.game.view.TitleScreenRenderer;

import java.awt.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the title screen state of the game.
 */
public class TitleState implements GameState {
    private static final Logger logger = LogManager.getLogger(TitleState.class);

    private final GamePanel gamePanel;
    private final TitleScreenRenderer titleScreenRenderer;

    /**
     * Constructs a new TitleState with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public TitleState(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.titleScreenRenderer = new TitleScreenRenderer(gamePanel);
        logger.info("TitleState initialized.");
    }

    /**
     * Updates the title screen state.
     */
    @Override
    public void update() {
        // Handle navigation on the title screen
        if (gamePanel.getKeyHandler().isUpPressed()) {
            titleScreenRenderer.commandNum--;
            if (titleScreenRenderer.commandNum < 0) {
                titleScreenRenderer.commandNum = 2;
            }
            logger.debug("Menu selection changed: commandNum={}", titleScreenRenderer.commandNum);
            gamePanel.getKeyHandler().reset();
        }
        if (gamePanel.getKeyHandler().isDownPressed()) {
            titleScreenRenderer.commandNum++;
            if (titleScreenRenderer.commandNum > 2) {
                titleScreenRenderer.commandNum = 0;
            }
            logger.debug("Menu selection changed: commandNum={}", titleScreenRenderer.commandNum);
            gamePanel.getKeyHandler().reset();
        }
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            logger.info("Enter pressed, performing action for commandNum={}", titleScreenRenderer.commandNum);
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
                logger.info("Starting new game.");
                gamePanel.setCurrentState(new PlayState(gamePanel));
                break;
            case 1:
                // Load game
                logger.info("Loading game.");
                break;
            case 2:
                // Quit game
                logger.info("Quitting game.");
                System.exit(0);
                break;
        }
    }
}
