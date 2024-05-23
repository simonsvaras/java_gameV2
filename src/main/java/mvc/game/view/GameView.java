package mvc.game.view;

import mvc.game.controller.C_GamePanel;
import mvc.game.state.GameState;

import java.awt.*;

public class GameView {
    private final C_GamePanel gamePanel;
    private final TileRenderer tileRenderer;
    // Můžete přidat další renderery pro různé herní prvky
    // private final PlayerRenderer playerRenderer;
    // private final EntityRenderer entityRenderer;
    // private final UIRenderer uiRenderer;

    public GameView(C_GamePanel gamePanel, TileRenderer tileRenderer) {
        this.gamePanel = gamePanel;
        this.tileRenderer = tileRenderer;
        // Inicializace dalších rendererů
        // this.playerRenderer = new PlayerRenderer(gamePanel);
        // this.entityRenderer = new EntityRenderer(gamePanel);
        // this.uiRenderer = new UIRenderer(gamePanel);
    }

    public void render(Graphics2D g2) {
        // Vykreslení aktuálního herního stavu
        GameState currentState = gamePanel.getCurrentState();

        if (currentState != null) {
            currentState.render(g2);
        }

        // Vykreslení mapy
        //tileRenderer.render(g2);

        // Vykreslení hráče
        // playerRenderer.render(g2);

        // Vykreslení entit
        // entityRenderer.render(g2);

        // Vykreslení uživatelského rozhraní
        // uiRenderer.render(g2);
    }
}