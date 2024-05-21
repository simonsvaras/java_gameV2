package org.game.main;

import org.game.entity.Entity;
import org.game.object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    BufferedImage heart_full, heart_half, heart_blank;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;

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

    public void  addMessage(String text){
        message.add(text);
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

        // CHARACTER STATE
        if(gamePanel.gameState == gamePanel.characterState){
            drawCharacterScreen();
            drawInventory();
        }

        // CHARACTER STATE
        if(gamePanel.gameState == gamePanel.optionState){
            drawOptionScreen();

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

    public void drawCharacterScreen(){
        // CREATE A FRAME
        final int frameX = gamePanel.tileSize*2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize*5;
        final int frameHeight = gamePanel.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(26F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 40;

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gamePanel.tileSize;
        String value;

        value = String.valueOf(gamePanel.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.life + "/" + gamePanel.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value,textX,textY);

        textY += lineHeight/2;

        g2.drawImage(gamePanel.player.currentWeapon.down1, tailX-gamePanel.tileSize, textY - 14, null );
        textY += gamePanel.tileSize;
        g2.drawImage(gamePanel.player.currentShield.down1, tailX - gamePanel.tileSize, textY - 14, null);
    }

    public void drawOptionScreen(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gamePanel.tileSize*4;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize*8;
        int frameHeight = gamePanel.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: break;
            case 2: option_control(frameX, frameY); break;
            case 3: option_endGameConfirmation(frameX,frameY); break;
        }

        gamePanel.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize + gamePanel.tileSize/2;
        g2.drawString("Full Screnn", textX,textY);
        if (commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyH.enterPressed){
                if(!gamePanel.fullScreenOn){
                    gamePanel.fullScreenOn = true;
                }
                else if(gamePanel.fullScreenOn){
                    gamePanel.fullScreenOn = false;
                }
            }
        }

        // MUSIC
        textY += gamePanel.tileSize;
        g2.drawString("Music", textX,textY);
        if (commandNum == 1){
            g2.drawString(">", textX - 25, textY);
        }
        // SE
        textY += gamePanel.tileSize;
        g2.drawString("SE", textX,textY);
        if (commandNum == 2){
            g2.drawString(">", textX - 25, textY);
        }

        // CONTROL
        textY += gamePanel.tileSize;
        g2.drawString("Control", textX,textY);
        if (commandNum == 3){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyH.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gamePanel.tileSize;
        g2.drawString("End Game", textX,textY);
        if (commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gamePanel.tileSize*2;
        g2.drawString("Back", textX,textY);
        if (commandNum == 5){
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyH.enterPressed){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        // FULL SCREEN CHECKBOX
        textX =  (frameX + gamePanel.tileSize * 5);
        textY = frameY + gamePanel.tileSize * 2 + 2;
        g2.setStroke((new BasicStroke(3)));
        g2.drawRect(textX,textY,24,24);
        if(gamePanel.fullScreenOn){
            g2.fillRect(textX,textY,24,24);
        }

        // MUSIC VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY, 120, 25); // 120/5 = 24
        int volumeWidth = 24 * gamePanel.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth, 24);

        // SE VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY, 120, 25);
        volumeWidth = 24 * gamePanel.soundEF.volumeScale;
        g2.fillRect(textX,textY, volumeWidth, 24);
;

    }
    public void option_control(int frameX, int frameY){
        int textX;
        int textY;

        g2.setFont(g2.getFont().deriveFont(24F));

        // TILE
        String text = "Control";
        textX  = getXForCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX,textY);

        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        g2.drawString("Move",textX,textY);
        textY += gamePanel.tileSize;

        g2.drawString("Confirm/attack",textX,textY);
        textY += gamePanel.tileSize;

        g2.drawString("Character Screen",textX,textY);
        textY += gamePanel.tileSize;

        g2.drawString("Pause",textX,textY);
        textY += gamePanel.tileSize;

        g2.drawString("Option",textX,textY);
        textY += gamePanel.tileSize;

        textX = frameX + gamePanel.tileSize * 5 + 10;
        textY = frameY + gamePanel.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("C", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("P", textX, textY); textY += gamePanel.tileSize;
        g2.drawString("ESC", textX, textY); textY += gamePanel.tileSize;

        // BACK
        textX = frameX + gamePanel.tileSize;
        textY =frameY + gamePanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gamePanel.keyH.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }

    }
    public void option_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize;

        currentDialogue = "Quit the game and \nreturn to the tittle screen?";


        g2.setFont(g2.getFont().deriveFont(22F));
        for (String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gamePanel.tileSize * 3;
        g2.drawString(text, textX,textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gamePanel.keyH.enterPressed){
                subState = 0;
                gamePanel.gameState = gamePanel.tittleState;
            }
        }
        // NO
        text = "No";
        textX = getXForCenteredText(text);
        textY += gamePanel.tileSize;
        g2.drawString(text, textX,textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gamePanel.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }


    }
    public  void drawInventory() {

        // FRAME
        int frameX = gamePanel.tileSize*9;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize*6;
        int frameHeight = gamePanel.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize + 3;

        //  DRAW PLAYERS ITEMS
        for (int i = 0; i < gamePanel.player.inventory.size(); i++){

            // EQUIP CURSOR
            if(gamePanel.player.inventory.get(i) == gamePanel.player.currentWeapon
                || gamePanel.player.inventory.get(i) == gamePanel.player.currentShield){
                g2.setColor(new Color(240, 190,90));
                g2.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2.drawImage(gamePanel.player.inventory.get(i).down1, slotX, slotY,null);

            // DISPLAY AMOUNT
            if(gamePanel.player.inventory.get(i).amount > 1){
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + gamePanel.player.inventory.get(i).amount;
                amountX = getXForAlignToRightText(s, slotX + 44);
                amountY = slotY + gamePanel.tileSize;

                // SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s,amountX, amountY);

                // NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);
            }

            slotX += slotSize;

            if(i == 4 || i == 14 || i == 9){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // CURSOR
        int cursorX = slotXStart + slotSize * slotCol;
        int cursorY = slotYStart + slotSize * slotRow;
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        // DRAW CURSOR
        g2.setStroke(new BasicStroke((3)));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.tileSize*3;



        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gamePanel.tileSize;

        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex <  gamePanel.player.inventory.size()){
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

            for(String line : gamePanel.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
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
        return gamePanel.screenWidth/2 - length/2;
    }

    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return tailX - length;
    }
}
