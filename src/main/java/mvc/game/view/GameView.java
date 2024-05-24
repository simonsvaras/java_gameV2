package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.state.GameState;

import java.awt.*;

/**
 * Manages the rendering of the game.
 */
public class GameView {
    private final C_GamePanel gamePanel;
    private final TileRenderer tileRenderer;

    /**
     * Constructs a new GameView with the specified game panel and tile renderer.
     *
     * @param gamePanel The game panel.
     * @param tileRenderer The tile renderer.
     */
    public GameView(C_GamePanel gamePanel, TileRenderer tileRenderer) {
        this.gamePanel = gamePanel;
        this.tileRenderer = tileRenderer;
    }

    /**
     * Renders the current game state.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void render(Graphics2D g2) {
        // Vykreslení aktuálního herního stavu
        GameState currentState = gamePanel.getCurrentState();

        if (currentState != null) {
            currentState.render(g2);
        }
    }
}
