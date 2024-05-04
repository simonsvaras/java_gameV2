package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.KeyHandler;
import org.game.main.UI;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    KeyHandler keyH;

    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;
    public static final int DEFAULT_AREA = 32;


    public Player(GamePanel gamePanel, KeyHandler keyH) {
        super(gamePanel);

        this.keyH = keyH;

        // making exact middle point of screen to be sure our player will be always in the middle
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        // SET COLLISION AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = DEFAULT_AREA;
        solidArea.width = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // Starting position
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        // speed
        speed = 5;
        // direction for sprites
        direction = "down";
    }

    public void getPlayerImage() {
        // SETUP SPRITES
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1= setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");


    }



    public void update () {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this,true);
            pickUpObject(objectIndex);

            // CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                }
            }

            // MOVING ANIMATION => change sprites every 10 cycles
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;

                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){

        if(index != 999){ // This means that we did not touch any object
            //This delete the object we just touch
            //gp.obj[index] =  null;
        }
    }

    public void interactNPC(int index){
        if(index != 999){
            // when player hitting NPC we can change game state

            if(gamePanel.keyH.enterPressed) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
            }
        }
        gamePanel.keyH.enterPressed = false;
    }
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
