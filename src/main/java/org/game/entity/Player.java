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

    GamePanel gp;
    KeyHandler keyH;

    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;
    public int hasKeys;

    public static final int DEFAULT_AREA = 32;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // making exact middle point of screen to be sure our player will be always in the middle
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        // speed
        speed = 5;
        // direction for sprites
        direction = "down";
    }

    public void getPlayerImage() {
        // SETUP SPRITES
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1= setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");


    }

    // SETUP SPRITES
    // Load sprite, scaled sprite
    /**
     * @param imageName
     * @return image
     */
    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
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
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gp.collisionChecker.checkObject(this,true);
            pickUpObject(objectIndex);

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

            String objectName = gp.obj[index].name;

            switch (objectName){
                case "Key":
                    hasKeys++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You got the key!");
                    gp.playSE(1);
                    break;

                case "Door":
                    if(hasKeys > 0){
                        gp.obj[index] = null;
                        hasKeys--;
                        gp.playSE(3);
                        gp.ui.showMessage("You opened the door");
                    }else {
                        gp.ui.showMessage("You need a key");
                    }

                    break;
                case "Boots":
                    speed += 1 ;
                    gp.obj[index] = null;
                    gp.playSE(2);
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.showMessage("Open a chest");
                    gp.playSE(4);

            }
        }
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
