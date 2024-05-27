package mvc.game.model.objects;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.Player;

import java.awt.*;

public class Weapon extends GameObject{
    /**
     * The attack value of the game object.
     */
    protected int attackValue;



    /**
     * Constructs a new game object with the specified game panel.
     *
     * @param gamePanel The game panel.
     * @param worldX
     * @param worldY
     */
    public Weapon(GamePanel gamePanel, int worldX, int worldY) {
        super(gamePanel, worldX, worldY);
    }

    @Override
    public void loadPlayerSprites() {

    }


    @Override
    public void interact(Player player) {
        // TODO
    }

    @Override
    public void pickUp(Player player) {
        // TODO
    }

    public int getAttackValue() {
        return attackValue;
    }
    public void setAttackValue(int attack) {
        attackValue = attack;
    }
}
