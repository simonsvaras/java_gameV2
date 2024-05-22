package mvc.game.model;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.invenory.Inventory;
import mvc.game.model.objects.OBJ_Potion_Red;
import mvc.game.model.objects.OBJ_Shield_Wood;
import mvc.game.model.objects.OBJ_Sword_Normal;
import org.game.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class M_Player extends M_Entity{
    private Inventory inventory;

    // Screen position => doesnt change
    public final int screenX;
    public final int screenY;
    public  boolean attackCanceled = false;

    protected BufferedImage upImage1;
    public BufferedImage downImage1;
    protected BufferedImage leftImage1;
    protected BufferedImage rightImage1;
    protected BufferedImage upImage2;
    protected BufferedImage downImage2;
    protected BufferedImage leftImage2;
    protected BufferedImage rightImage2;
    protected BufferedImage attackUpImages1, attackDownImages1, attackLeftImages1, attackRightImages1;
    protected BufferedImage attackUpImages2, attackDownImages2, attackLeftImages2, attackRightImages2;


    public static final int DEFAULT_AREA = 32;

    public M_Player(C_GamePanel gamePanel) {
        super(gamePanel);
        this.inventory = new Inventory();

        // making exact middle point of screen to be sure our player will be always in the middle
        screenX = C_GamePanel.SCREEN_WIDTH /2 - (C_GamePanel.TILE_SIZE /2);
        screenY = C_GamePanel.SCREEN_HEIGHT /2 - (C_GamePanel.TILE_SIZE /2);

        // SET COLLISION AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = DEFAULT_AREA;
        solidArea.width =DEFAULT_AREA;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }


    public void setDefaultValues() {
        // Starting position
        worldX = C_GamePanel.TILE_SIZE * 23;
        worldY = C_GamePanel.TILE_SIZE * 21;
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
    public void setItems(){
        inventory.addItem(currentWeapon);
        inventory.addItem(currentShield);
        inventory.addItem(new OBJ_Potion_Red(gamePanel));
        inventory.addItem(new OBJ_Potion_Red(gamePanel));
    }

    // Total attack value is decided by strength and weapon
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    // Total defense value is decided by dexterity adn shield
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }


    public void getPlayerImage() {
        // SETUP SPRITES
        upImage1 = setup("/player/boy_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        upImage2 = setup("/player/boy_up_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        downImage1= setup("/player/boy_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        downImage2 = setup("/player/boy_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        leftImage1 = setup("/player/boy_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        leftImage2 = setup("/player/boy_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        rightImage1 = setup("/player/boy_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        rightImage2 = setup("/player/boy_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
    }

    public void getPlayerAttackImage() {
        if (currentWeapon.type == TYPE_SWORD) {
            attackUpImages1 = setup("/player/Attacking/boy_attack_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackUpImages2 = setup("/player/Attacking/boy_attack_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackDownImages1 = setup("/player/Attacking/boy_attack_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackDownImages2 = setup("/player/Attacking/boy_attack_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackLeftImages1 = setup("/player/Attacking/boy_attack_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackLeftImages2 = setup("/player/Attacking/boy_attack_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackRightImages1 = setup("/player/Attacking/boy_attack_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackRightImages2 = setup("/player/Attacking/boy_attack_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        } else if (currentWeapon.type == TYPE_AXE) {
            attackUpImages2 = setup("/player/Attacking/boy_axe_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackUpImages1 = setup("/player/Attacking/boy_axe_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackDownImages1 = setup("/player/Attacking/boy_axe_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackDownImages2 = setup("/player/Attacking/boy_axe_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackLeftImages1 = setup("/player/Attacking/boy_axe_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackLeftImages2 = setup("/player/Attacking/boy_axe_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackRightImages1 = setup("/player/Attacking/boy_axe_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
            attackRightImages2 = setup("/player/Attacking/boy_axe_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        }
    }


    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }


}
