package mvc.game.model.entity;

import mvc.game.model.Coordinate;
import mvc.game.model.EntityType;
import mvc.game.controller.GamePanel;
import mvc.game.utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Abstract class representing an entity in the game.
 */
public abstract class Entity {
    protected GamePanel gamePanel;

    /**
     * The position of the entity in the world.
     */
    protected Coordinate position;

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
     * @param worldX The x-coordinate of the entity in the world.
     * @param worldY The y-coordinate of the entity in the world.
     */
    public Entity(GamePanel gamePanel, int worldX, int worldY) {
        this.gamePanel = gamePanel;
        this.position = new Coordinate(worldX, worldY);
        this.solidArea = new Rectangle(0, 0, 48, 48);
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.attackArea = new Rectangle(0, 0, 0, 0);
        this.collision = false;
        this.spriteNum = 0;
        this.invincible = false;
        this.collisionOn = false;
    }




    /**
     * Abstract method to load player sprites.
     */
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public int getWorldX() {
        return position.getX();
    }

    public void setWorldX(int worldX) {
        position.setX(worldX);
    }

    public int getWorldY() {
        return position.getY();
    }

    public void setWorldY(int worldY) {
        position.setY(worldY);
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }

    public void setAttackArea(Rectangle attackArea) {
        this.attackArea = attackArea;
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

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }


    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
