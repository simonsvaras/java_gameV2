package org.game.main;

import org.game.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        // SET A FONT
        arial_40 = new Font("Arial", Font.PLAIN,40);
        // For key icon
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawString(" x " + gamePanel.player.hasKeys, 75, 65);

        // TIME
         playTime += (double) 1/60;
         g2.drawString("Time:" + decimalFormat.format(playTime), gamePanel.tileSize*11, 65);

        // MESSAGE
        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30));
            g2.drawString(message,gamePanel.tileSize/2,gamePanel.tileSize*5);
            messageCounter++;
            if (messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
