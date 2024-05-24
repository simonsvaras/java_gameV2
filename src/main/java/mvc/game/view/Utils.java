package mvc.game.view;

import mvc.game.controller.C_GamePanel;

import java.awt.*;


public class Utils {
    C_GamePanel gamePanel;

    public int getXForCenteredText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return C_GamePanel.SCREEN_WIDTH/2 - length/2;
    }
}
