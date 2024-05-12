package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.KeyHandler;
import org.game.object.OBJ_Shield_Wood;
import org.game.object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;

    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;
    public  boolean attackCanceled = false;
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

        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        // Starting position
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        // speed
        speed = 5;
        // direction for sprites
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gamePanel);
        currentShield = new OBJ_Shield_Wood(gamePanel);
        attack = getAttack();
        defense = getDefense();
    }

    // Total attack value is decided by strength and weapon
    public int getAttack(){
        return attack = strength * currentWeapon.attackValue;
    }

    // Total defense value is decided by dexterity adn shield
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        // SETUP SPRITES
        up1 = setup("/player/boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/player/boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1= setup("/player/boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/player/boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/player/boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/player/boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/player/boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/player/boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }


    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/Attacking/boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackUp2 = setup("/player/Attacking/boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown1 = setup("/player/Attacking/boy_attack_down_1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown2 = setup("/player/Attacking/boy_attack_down_2", gamePanel.tileSize, gamePanel.tileSize*2);
        attackLeft1 = setup("/player/Attacking/boy_attack_left_1", gamePanel.tileSize*2, gamePanel.tileSize);
        attackLeft2 = setup("/player/Attacking/boy_attack_left_2", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight1 = setup("/player/Attacking/boy_attack_right_1", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight2 = setup("/player/Attacking/boy_attack_right_2", gamePanel.tileSize*2, gamePanel.tileSize);


    }


    public void update () {
        if(isAttacking){
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed){
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

            // CHECK MONSTER COLLISION
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            interactMonster(monsterIndex);

            // CHECK EVENT
            gamePanel.eventHandler.checkEvent();



            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn && !keyH.enterPressed){
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                }
            }

            if(keyH.enterPressed == true && attackCanceled == false){
                gamePanel.playSE(7);
                isAttacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gamePanel.keyH.enterPressed = false;

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

        if(invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            // Sae current worldX, worldY, solidArea
            int currentWordX = worldX;
            int currentWordY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust players wordX/Y for the attack
            switch (direction){
                case "up": worldY -= attackArea.height;break;
                case "down": worldY += attackArea.height;break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width;break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with update worldX, worldY and solidAred
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            damageMonster(monsterIndex);

            worldX = currentWordX;
            worldY = currentWordY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }

    public void pickUpObject(int index){

        if(index != 999){ // This means that we did not touch any object
            //This delete the object we just touch
            //gp.obj[index] =  null;
        }
    }

    public void interactMonster(int index){
        if(index != 999){
            if(!invincible){
                gamePanel.playSE(6);
                life -=  1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int index){
        if( index != 999){
            if (!gamePanel.monster[index].invincible){
                gamePanel.playSE(5);
                gamePanel.monster[index].life --;
                gamePanel.monster[index].invincible = true;
                gamePanel.monster[index].damageReaction();

                // Kill the monster
                if (gamePanel.monster[index].life <= 0){
                    gamePanel.monster[index].dying = true;
                }
            }
        }
    }

    public void interactNPC(int index){
        if (gamePanel.keyH.enterPressed) {
            if (index != 999) {
                // when player hitting NPC we can change game state
                attackCanceled = true;
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
            }
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if(!isAttacking) {
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if (isAttacking){
                    tempScreenY = screenY - gamePanel.tileSize;
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
                }
                break;

            case "down":
                if(!isAttacking) {
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if (isAttacking){
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
                }
                break;

            case "left":
                if(!isAttacking) {
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if(isAttacking){
                    tempScreenX = screenX - gamePanel.tileSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft2;
                }
                break;

            case "right":
                if(!isAttacking) {
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                if(isAttacking){
                    if(spriteNum == 1) image = attackRight2;
                    if(spriteNum == 2) image = attackRight2;
                }
                break;
        }

        //  VISUAL EFFECT TO INVINCIBLE STATE
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

    }
}
