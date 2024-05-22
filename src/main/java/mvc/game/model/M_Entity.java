package mvc.game.model;

import mvc.game.controller.C_GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class M_Entity {
    protected C_GamePanel gamePanel;

    protected BufferedImage upImage1;
    public BufferedImage downImage1;
    protected BufferedImage leftImage1;
    protected BufferedImage rightImage1;
    protected BufferedImage upImage2;
    protected BufferedImage downImage2;
    protected BufferedImage leftImage2;
    protected BufferedImage rightImage2;
    protected BufferedImage attackUpImages, attackDownImages, attackLeftImages, attackRightImages;
    public BufferedImage image, image2, image3;
    protected Rectangle solidArea;
    protected Rectangle attackArea;
    protected boolean collision;
    public String[] dialogues = new String[20];

    // STATE
    public int worldX;
    public int worldY;
    protected String direction;
    protected int spriteNum;
    protected boolean invincible;
    protected boolean collisionOn;
    protected int dialogueIndex;
    protected boolean isAttacking;
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
        spriteNum = 1;
        invincible = false;
        collisionOn = false;
        dialogueIndex = 0;
        isAttacking = false;
        alive = true;
        dying = false;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
    public abstract void setAction();
    public abstract void damageReaction();
    public abstract void checkDrop();

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gamePanel.player.direction) {
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

    public void use(M_Entity entity) {}

    public void dropItem(M_Entity droppedItem) {
        for (int i = 0; i < gamePanel.obj.length; i++) {
            if (gamePanel.obj[i] == null) {
                gamePanel.obj[i] = droppedItem;
                gamePanel.obj[i].worldX = worldX;
                gamePanel.obj[i].worldY = worldY;
                break;
            }
        }
    }

    protected void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int interval = 8;
        if (dyingCounter <= interval * 8) {
            float alphaValue = (dyingCounter / interval) % 2 == 0 ? 0.0f : 1.0f;
            changeAlpha(g2, alphaValue);
        } else {
            dying = false;
            alive = false;
        }
    }

    private void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        /*
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

         */
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