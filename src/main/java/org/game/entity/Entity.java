package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Parent class for the PLAYER and other character classes (monsters, NPC atc.) and objects
 * Abstract class
 */

public class Entity {
    GamePanel gamePanel;

    // Start position of Player



    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public boolean collision = false;
    public String[] dialogues = new String[20];
    public int solidAreaDefaultX, solidAreaDefaultY;

    // STATE
    public int worldX, worldY;
    public  String direction = "down";
    public int spriteNum = 1;
    public boolean invincible = false;
    public boolean collisionOn = false;
    public int dialogueIndex = 0;
    public boolean isAttacking = false;
    public boolean alive = true;
    public boolean dying = false;

    // COUNTER
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;


    // CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int strength;
    public int dexterity;
    public int defense;
    public int attack;
    public int level;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    // ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";

    // TYPE
    public int type; // 0 == player, 1 = npc, 2, = monster

    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;


    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void setAction(){}
    public void damageReaction(){}

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

    public void use(Entity entity){}

     public void update(){
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this,false);
        gamePanel.collisionChecker.checkEntity(this,gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer){
            if(!gamePanel.player.invincible){
                // We can give damage
                gamePanel.playSE(6);

                int damage = attack - gamePanel.player.defense;
                if(damage < 0)
                    damage = 0;
                gamePanel.player.life -=  damage;

                gamePanel.player.invincible = true;
            }
        }

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

         if(invincible){
             invincibleCounter++;
             if (invincibleCounter > 40){
                 invincible = false;
                 invincibleCounter = 0;
             }
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
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    break;
                case "down":
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    break;
                case "left":
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    break;
                case "right":
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    break;
            }

            // Monster healthBar
            if(type == 2) {
                double oneScale = (double)gamePanel.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-13,gamePanel.tileSize+2,12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 12, (int) hpBarValue, 10);
            }

            //  VISUAL EFFECT TO INVINCIBLE STATE
            if (invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            if(dying){
                dyingAnimation(g2);
            }


            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        //int i = 5;

        int interval = 8;

        // Toggle alpha values between 0.0 and 1.0 in intervals of `interval`
        if (dyingCounter <= interval * 8) {
            float alphaValue = (dyingCounter / interval) % 2 == 0 ? 0.0f : 1.0f;
            changeAlpha(g2, alphaValue);
        } else {
            dying = false;
            alive = false;
        }
    }

    private void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }


    // SETUP SPRITES
    // Load sprite, scaled sprite
    /**
     * @param imagePath
     * @return image
     */
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }


}
