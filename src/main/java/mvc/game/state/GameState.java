package mvc.game.state;

import mvc.game.controller.C_GamePanel;

import java.awt.*;

/**
 * Interface representing a state in the game.
 */
public interface GameState {

    /**
     * Updates the game state.
     */
    void update();

    /**
     * Renders the game state.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    void render(Graphics2D g2);
}
