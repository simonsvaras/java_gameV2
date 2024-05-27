package mvc.game;

import mvc.game.controller.GamePanel;
import mvc.game.model.EntityType;
import mvc.game.model.objects.Potion;
import mvc.game.model.objects.Shield;
import mvc.game.model.objects.Weapon;

import java.awt.*;

public class ObjectFactory {

    private ObjectFactory() {
        // Private constructor to prevent instantiation
    }


    public static Weapon createAxe(GamePanel gamePanel){
        Weapon axe = new Weapon(gamePanel, 0, 0);
        axe.setName("Axe");
        axe.setType(EntityType.SWORD);
        axe.setAttackValue(1);
        Rectangle attack = axe.getAttackArea();
        attack.width =  30;
        attack.height = 30;
        axe.setAttackArea(attack);
        axe.setDescription("[" + axe.getName() + "]\nAn odl sword");
        axe.setImage(axe.setup("/objects/sword_normal", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

        return axe;
    }


    public static Weapon createNormalSword(GamePanel gamePanel){
        Weapon weapon = new Weapon(gamePanel, 0, 0);
        weapon.setName("Normal Sword");
        weapon.setType(EntityType.SWORD);
        weapon.setAttackValue(2);
        Rectangle attack = weapon.getAttackArea();
        attack.width = 40;
        attack.height = 40;
        weapon.setAttackArea(attack);
        weapon.setDescription("[" + weapon.getName() + "]\nAn odl sword");
        weapon.setImage(weapon.setup("/objects/sword_normal", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

        return weapon;
    }

    public static Shield createWoodShield(GamePanel gamePanel){
        Shield shield = new Shield(gamePanel, 0, 0);
        shield.setName("Wood shield");
        shield.setType(EntityType.SHIELD);
        shield.setDefenseValue(1);
        shield.setDescription("[" + shield.getName() + "]\nIs made by wood");
        shield.setImage(shield.setup("/objects/shield_wood", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        return shield;
    }

    public static Potion createPotionRed(GamePanel gamePanel){
        Potion potion = new Potion(gamePanel, 0, 0);
        potion.setName("Red potion");
        potion.setHealAmount(5);
        potion.setStackable(true);
        potion.setType(EntityType.CONSUMABLE);
        potion.setDescription("[Red Potion] \n Heals your life by " + potion.getHealAmount() + ".");
        potion.setImage(potion.setup("/objects/potion_red", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

        return potion;
    }

}
