package mvc.game.state;

import mvc.game.controller.C_GamePanel;
import mvc.game.view.TitleScreenRenderer;

import java.awt.*;

public class TitleState implements GameState{
    private final C_GamePanel gamePanel;
    private final TitleScreenRenderer titleScreenRenderer;

    public TitleState(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.titleScreenRenderer = new TitleScreenRenderer(gamePanel);
    }

    @Override
    public void update(C_GamePanel gamePanel) {
        // Logika aktualizace pro TitleState
    }

    @Override
    public void render(Graphics2D g2) {
        titleScreenRenderer.drawTitleScreen(g2);
    }

    @Override
    public void exit(C_GamePanel gamePanel) {
        // Logika pro ukončení TitleState, pokud je potřeba
    }

    @Override
    public void enter(C_GamePanel gamePanel) {
        // Logika pro inicializaci TitleState, pokud je potřeba
    }
}
