package mvc.game.state;

import mvc.game.controller.C_GamePanel;

import java.awt.*;

public interface GameState {
    void update(C_GamePanel gamePanel);
    void render(Graphics2D g2);
}
