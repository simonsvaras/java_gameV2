package mvc.game.model.entity;

import mvc.game.ObjectFactory;
import mvc.game.model.EntityType;
import mvc.game.controller.GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.invenory.Inventory;
import mvc.game.model.objects.GameObject;
import mvc.game.model.objects.Shield;
import mvc.game.model.objects.Weapon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * Represents the player in the game.
 */
public class Player extends LiveObjects {

    private static final Logger logger = LogManager.getLogger(Player.class);
    private Inventory inventory;

    /**
     * Indicates if the attack was canceled.
     */
    public boolean attackCanceled = false;

    /**
     * The current weapon of the player.
     */
    private Weapon currentWeapon;

    /**
     * The current shield of the player.
     */
    private Shield currentShield;

    /**
     * The default collision area size.
     */
    private static final int DEFAULT_AREA = 32;

    /**
     * Constructs a new player with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public Player(GamePanel gamePanel) {
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
        setWorldX(GamePanel.TILE_SIZE * 23);
        setWorldY(GamePanel.TILE_SIZE * 23);
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

        currentWeapon = ObjectFactory.createNormalSword(gamePanel);
        currentShield = ObjectFactory.createWoodShield(gamePanel);
        attack = calculateAttack();
        defense = calculateDefense();

        // SET COLLISION AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = DEFAULT_AREA;
        solidArea.width = DEFAULT_AREA;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        logger.debug("Default player values set: X={}, Y={}, Level={}, Life={}", position.getX(), position.getY(), level, life);
    }

    /**
     * Sets the initial items for the player.
     */
    public void setItems() {
        inventory.addItem(currentWeapon);
        inventory.addItem(currentShield);
        inventory.addItem(ObjectFactory.createPotionRed(gamePanel));
        inventory.addItem(ObjectFactory.createPotionRed(gamePanel));
        logger.info("Initial items set. Inventory contains {} items.", inventory.getItems().size());
    }

    /**
     * Calculates the total attack value based on strength and weapon.
     *
     * @return The total attack value.
     */
    public int calculateAttack() {
        attackArea = currentWeapon.attackArea;
        logger.debug("Total attack calculated: {}", attack);
        return attack = strength * currentWeapon.getAttackValue();
    }

    /**
     * Calculates the total defense value based on dexterity and shield.
     *
     * @return The total defense value.
     */
    public int calculateDefense() {
        defense = dexterity * currentShield.getDefenseValue();
        logger.debug("Total defense calculated: {}", defense);
        return defense;
    }

    /**
     * Loads the player's movement sprites.
     */
    @Override
    public void loadPlayerSprites() {
        sprite.loadSprite("up", setup("/player/boy_up_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/boy_up_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/player/boy_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/boy_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/player/boy_left_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/boy_left_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/player/boy_right_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/boy_right_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
    }

    /**
     * Loads the player's attack sprites based on the current weapon.
     */
    public void loadPlayersAttackSprites() {
        if (currentWeapon.type == EntityType.SWORD) {
            sprite.loadSprite("attack_up", setup("/player/Attacking/boy_attack_up_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_up_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_down", setup("/player/Attacking/boy_attack_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_left", setup("/player/Attacking/boy_attack_left_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_left_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_right", setup("/player/Attacking/boy_attack_right_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_attack_right_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        } else if (currentWeapon.type == EntityType.AXE) {
            sprite.loadSprite("attack_up", setup("/player/Attacking/boy_axe_up_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_up_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_down", setup("/player/Attacking/boy_axe_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_left", setup("/player/Attacking/boy_axe_left_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_left_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
            sprite.loadSprite("attack_right", setup("/player/Attacking/boy_axe_right_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/player/Attacking/boy_axe_right_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
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
