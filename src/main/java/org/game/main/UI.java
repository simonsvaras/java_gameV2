package org.game.main;

import org.game.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    boolean gameFinished = false;
    public String currentDialogue = "";


    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        // SET A FONT
        arial_40 = new Font("Arial", Font.PLAIN,40);

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // PLAY STATE
        if(gamePanel.gameState == gamePanel.playState){
            // TODO
        }

        // PAUSE STATE
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gamePanel.gameState == gamePanel.dialogueState){
            drasDialogueScreen();
        }

    }

    public void drasDialogueScreen(){
        // WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        // Split text in dialogue to fit in sub window
        for (String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height, 35,35);

        c = new Color(255,255,210);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5, width-10, height-10, 25,25);
    }

    public  void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSE";

        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight/2;

        g2.drawString(text, x,y);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}
