package mvc.game.model.entity;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.invenory.Inventory;
import mvc.game.model.objects.GameObject;
import mvc.game.model.objects.OBJ_Potion_Red;
import mvc.game.model.objects.OBJ_Shield_Wood;
import mvc.game.model.objects.OBJ_Sword_Normal;

import java.awt.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the player in the game.
 */
public class M_Player extends LiveObjects {

    private static final Logger logger = LogManager.getLogger(M_Player.class);
    private Inventory inventory;

    /**
     * Indicates if the attack was canceled.
     */
    public boolean attackCanceled = false;

    /**
     * The current weapon of the player.
     */
    protected GameObject currentWeapon;

    /**
     * The current shield of the player.
     */
    protected GameObject currentShield;

    /**
     * The default collision area size.
     */
    public static final int DEFAULT_AREA = 32;

    /**
     * Constructs a new player with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public M_Player(C_GamePanel gamePanel) {
        super(gamePanel);
        this.inventory = new Inventory();


        setDefaultValues();
        loadPlayerSprites();
        loadPlayersAttackSprites();
        setItems();
    }

    /**
     * Sets the default values for the player.
     */
    public void setDefaultValues() {
        // Starting position
        worldX = C_GamePanel.TILE_SIZE * 23;
        worldY = C_GamePanel.TILE_SIZE * 23;
        // Speed
        speed = 5;
        // Direction for sprites
        direction = Direction.DOWN;

        name = "Blue Player";

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

        // SET COLLISION AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = DEFAULT_AREA;
        solidArea.width = DEFAULT_AREA;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        logger.debug("Default player values set: X={}, Y={}, Level={}, Life={}", worldX, worldY, level, life);
    }

    /**
     * Sets the initial items for the player.
     */
    public void setItems() {
        inventory.addItem(currentWeapon);
        inventory.addItem(currentShield);
        inventory.addItem(new OBJ_Potion_Red(gamePanel));
        inventory.addItem(new OBJ_Potion_Red(gamePanel));
        logger.info("Initial items set. Inventory contains {} items.", inventory.getItems().size());

    }

    /**
     * Calculates the total attack value based on strength and weapon.
     *
     * @return The total attack value.
     */
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        logger.debug("Total attack calculated: {}", attack);
        return attack = strength * currentWeapon.attackValue;
    }

    /**
     * Calculates the total defense value based on dexterity and shield.
     *
     * @return The total defense value.
     */
    public int getDefense() {
        defense = dexterity * currentShield.defenseValue;
        logger.debug("Total defense calculated: {}", defense);
        return defense;
    }

    /**
     * Loads the player's movement sprites.
     */
    public void loadPlayerSprites() {
        sprite.loadSprite("up", setup("/player/boy_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/boy_up_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/player/boy_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/boy_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/player/boy_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/boy_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/player/boy_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/boy_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
    }

    /**
     * Loads the player's attack sprites based on the current weapon.
     */
    public void loadPlayersAttackSprites() {
        if (currentWeapon.type == TYPE_SWORD) {
            sprite.loadSprite("attack_up", setup("/player/Attacking/boy_attack_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_up_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_down", setup("/player/Attacking/boy_attack_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_left", setup("/player/Attacking/boy_attack_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_right", setup("/player/Attacking/boy_attack_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        } else if (currentWeapon.type == TYPE_AXE) {
            sprite.loadSprite("attack_up", setup("/player/Attacking/boy_axe_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_up_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_down", setup("/player/Attacking/boy_axe_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_left", setup("/player/Attacking/boy_axe_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_right", setup("/player/Attacking/boy_axe_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        }
    }

    /**
     * Returns the player's inventory.
     *
     * @return The inventory of the player.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
