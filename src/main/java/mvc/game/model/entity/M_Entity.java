package mvc.game.model.entity;

import mvc.game.model.EntityType;
import mvc.game.controller.C_GamePanel;
import mvc.game.utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Abstract class representing an entity in the game.
 */
public abstract class M_Entity {
    protected C_GamePanel gamePanel;

    /**
     * The solid area for collision detection.
     */
    protected Rectangle solidArea;

    /**
     * The attack area for the entity.
     */
    protected Rectangle attackArea;

    /**
     * Indicates if the entity is colliding with something.
     */
    protected boolean collision;

    // STATE
    /**
     * The x-coordinate of the entity in the world.
     */
    protected int worldX;

    /**
     * The y-coordinate of the entity in the world.
     */
    protected int worldY;

    /**
     * The current sprite number.
     */
    protected int spriteNum;

    /**
     * Indicates if the entity is invincible.
     */
    protected boolean invincible;

    /**
     * Indicates if collision is currently active for the entity.
     */
    protected boolean collisionOn;

    // COUNTER
    /**
     * Counter for sprite animation.
     */
    protected int spriteCounter;

    /**
     * The default x-coordinate of the solid area.
     */
    protected int solidAreaDefaultX;

    /**
     * The default y-coordinate of the solid area.
     */
    protected int solidAreaDefaultY;

    /**
     * The name of the entity.
     */
    protected String name;

    // TYPE
    /**
     * The type of the entity.
     */
    protected EntityType type;

    /**
     * Constructs a new entity with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public M_Entity(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(0, 0, 48, 48);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea = new Rectangle(0, 0, 0, 0);
        collision = false;
        spriteNum = 0;
        invincible = false;
        collisionOn = false;
    }
    public abstract void loadPlayerSprites();

    /**
     * Sets up and loads sprites with the specified width and height.
     *
     * @param imagePath The path to the image.
     * @param width     The width of the sprite.
     * @param height    The height of the sprite.
     * @return The loaded and scaled image.
     */
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

    /**
     * Resets the animation counters for the entity.
     */
    public void resetAnimation() {
        spriteNum = 0;
        spriteCounter = 0;
    }




    // Getters and setters for encapsulated fields
    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getSpriteNum() {
        return spriteNum;
    }


    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    public boolean getCollisionOn(){
        return collisionOn;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return  name;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }


}