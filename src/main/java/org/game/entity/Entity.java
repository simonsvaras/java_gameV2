package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Parent class for the PLAYER and other character classes (monsters, NPC atc.)
 * Abstract class
 */

public class Entity {
    GamePanel gamePanel;

    // Start position of Player
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    //All characters can share this default solid area
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void setAction(){}

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gamePanel.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }


     public void update(){
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this,false);
        gamePanel.collisionChecker.checkPlayer(this);

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

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; // offset the difference when player is in the corner
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

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

            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }


    // SETUP SPRITES
    // Load sprite, scaled sprite
    /**
     * @param imagePath
     * @return image
     */
    public BufferedImage setup(String imagePath){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }


}
