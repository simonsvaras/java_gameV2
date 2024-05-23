package mvc.game.state;

import mvc.game.controller.C_GamePanel;

import java.awt.*;

public interface GameState {
    void update();
    void render(Graphics2D g2);

}
