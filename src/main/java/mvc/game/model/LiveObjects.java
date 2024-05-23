package mvc.game.model;

import mvc.game.controller.C_GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LiveObjects extends M_Entity{

    protected Sprites sprite = new Sprites();


    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;

    // CHARACTER ATTRIBUTES
    public Direction direction;
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


    public boolean isAttacking;
    protected boolean alive;
    protected boolean dying;

    public String[] dialogues = new String[20];
    protected int dialogueIndex;


    public LiveObjects(C_GamePanel gamePanel) {
        super(gamePanel);
        // making exact middle point of screen to be sure our player will be always in the middle
        screenX = C_GamePanel.SCREEN_WIDTH /2 - (C_GamePanel.TILE_SIZE /2);
        screenY = C_GamePanel.SCREEN_HEIGHT /2 - (C_GamePanel.TILE_SIZE /2);

        isAttacking = false;
        alive = true;
        dying = false;
        dialogueIndex = 0;
    }

    public void moveUp() {
        direction = Direction.UP;
        worldY -= speed;
    }

    public void moveDown() {
        direction = Direction.DOWN;
        worldY += speed;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
        worldX -= speed;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
        worldX += speed;
    }


    public BufferedImage getCurrentFrame(int frameIndex) {
        String actionKey = direction.toString().toLowerCase();
        if (isAttacking) {
            actionKey = "attack_" + actionKey;
        }
        return sprite.getFrame(actionKey, frameIndex);
    }

    public void updateMovementFrame() {
        spriteCounter++;
        if (spriteCounter > 15) {
            spriteCounter = 0;
            if(spriteNum == 0){
                spriteNum = 1;
            }else {
                spriteNum = 0;
            }
        }
    }

    public int getSpeed(){
        return speed;
    }

}
