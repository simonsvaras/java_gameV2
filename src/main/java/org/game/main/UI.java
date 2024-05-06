package org.game.main;

import org.game.entity.Entity;
import org.game.object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        // SET A FONT
        arial_40 = new Font("Arial", Font.PLAIN,40);

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);


        // TITTLE STATE
        if(gamePanel.gameState == gamePanel.tittleState){
            drawTittleScreen();
        }

        // PLAY STATE
        if(gamePanel.gameState == gamePanel.playState){
            drawPlayerLife();
        }

        // PAUSE STATE
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gamePanel.gameState == gamePanel.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }

    }

    public void drawPlayerLife(){

        // DRAW BLANK HEARTS
        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;

        for (int i = 0; i < gamePanel.player.maxLife / 2; i++){
            g2.drawImage(heart_blank, x,y,null);
            x += gamePanel.tileSize;
        }


        // DRAW PLAYERS LIFES
        x = gamePanel.tileSize/2;
        y = gamePanel.tileSize/2;

        for (int i = 0; i < gamePanel.player.life / 2; i++){
            g2.drawImage(heart_full, x,y,null);
            x += gamePanel.tileSize;

        }

        if (gamePanel.player.life % 2 == 1){
            g2.drawImage(heart_half, x,y,null);
        }



    }
    public void drawTittleScreen(){

        g2.setColor(new Color(70,120,80));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);

        // TITTLE NAME
        g2.setFont((g2.getFont().deriveFont(Font.BOLD, 60F)));
        String text = "Blue boy adventure";
        int x = getXForCenteredText(text);
        int y = gamePanel.tileSize * 3;

        // SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);


        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // BLUE BOY IMAGE
        x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
        y += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize * 4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gamePanel.tileSize,y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gamePanel.tileSize,y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-gamePanel.tileSize,y);
        }
    }

    public void drawDialogueScreen(){
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
