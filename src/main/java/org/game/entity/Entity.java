package org.game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Parent class for the PLAYER and other character classes (monsters, NPC atc.)
 */

public class Entity {

    // Start position of Player
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
