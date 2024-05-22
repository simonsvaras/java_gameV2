package mvc.game.state;

import mvc.game.controller.C_GamePanel;
import mvc.game.controller.C_KeyHandler;
import mvc.game.model.M_Player;


import java.awt.*;

public class PlayState implements GameState{
    private M_Player player;
    @Override
    public void update(C_GamePanel gamePanel) {
        handlePlayerMovement(gamePanel);
    }

    private void handlePlayerMovement(C_GamePanel gamePanel) {
        C_KeyHandler keyHandler = gamePanel.getKeyHandler();
        if (gamePanel.getKeyHandler().upPressed) {
            gamePanel.player.moveUp();
        }
        if (gamePanel.getKeyHandler().downPressed) {
            gamePanel.player.moveDown();
        }
        if (gamePanel.getKeyHandler().leftPressed) {
            gamePanel.player.moveLeft();
        }
        if (gamePanel.getKeyHandler().rightPressed) {
            gamePanel.player.moveRight();
        }
    }

    @Override
    public void render(Graphics2D g2) {
        // Vykreslení herního stavu, hráče, nepřátel, mapy atd.
        player.draw(g2);
    }

    @Override
    public void exit(C_GamePanel gamePanel) {

    }

    @Override
    public void enter(C_GamePanel gamePanel) {

    }
}
