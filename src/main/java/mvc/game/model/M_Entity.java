package mvc.game.model;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.objects.Interactable;
import mvc.game.utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class M_Entity {
    protected C_GamePanel gamePanel;


    public Rectangle solidArea;
    protected Rectangle attackArea;
    protected boolean collision;

    // STATE
    public int worldX;
    public int worldY;
    public int spriteNum;
    protected boolean invincible;
    public boolean collisionOn;


    // COUNTER
    protected int spriteCounter;

    public int solidAreaDefaultX, solidAreaDefaultY;


    // TYPE
    protected int type;


    public static final int TYPE_PLAYER = 0;
    public static final int TYPE_NPC = 1;
    public static final int TYPE_MONSTER = 2;
    public static final int TYPE_SWORD = 3;
    public static final int TYPE_AXE = 4;
    public static final int TYPE_SHIELD = 5;
    public static final int TYPE_CONSUMABLE = 6;
    public static final int TYPE_PICKUP_ONLY = 7;



    public M_Entity(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(0, 0, 48, 48);
        attackArea = new Rectangle(0, 0, 0, 0);
        collision = false;
        spriteNum = 0;
        invincible = false;
        collisionOn = false;
    }


    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;


    }


    public void resetAnimation(){
        spriteNum = 0;
        spriteCounter = 0;
    }
}