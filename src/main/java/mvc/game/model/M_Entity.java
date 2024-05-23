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


    public BufferedImage image, image2, image3;
    public Rectangle solidArea;
    protected Rectangle attackArea;
    protected boolean collision;
    public String[] dialogues = new String[20];

    // STATE
    public int worldX;
    public int worldY;
    public String direction = "down";
    public int spriteNum;
    protected boolean invincible;
    public boolean collisionOn;
    protected int dialogueIndex;
    public boolean isAttacking;
    protected boolean alive;
    protected boolean dying;

    // COUNTER
    protected int actionLockCounter;
    protected int spriteCounter;
    protected int invincibleCounter;
    protected int dyingCounter;

    // CHARACTER ATTRIBUTES
    protected String name;
    protected int speed;
    protected int maxLife;
    protected int life;
    protected int strength;
    protected int dexterity;
    protected int defense;
    protected int attack;
    protected int level;
    protected int exp;
    protected int nextLevelExp;
    protected int coin;
    protected M_Entity currentWeapon;
    protected M_Entity currentShield;

    public int solidAreaDefaultX, solidAreaDefaultY;

    // ITEM ATTRIBUTES
    protected int attackValue;
    protected int value;
    protected int defenseValue;
    protected String description;
    protected boolean stackable;
    protected int amount;

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
        direction = "down";
        spriteNum = 0;
        invincible = false;
        collisionOn = false;
        dialogueIndex = 0;
        isAttacking = false;
        alive = true;
        dying = false;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);

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

    public void moveUp() {
    }

    public void moveDown() {
    }

    public void moveLeft() {
    }

    public void moveRight() {
    }
}