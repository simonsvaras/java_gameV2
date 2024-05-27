package mvc.game.model.objects;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.Player;

public class Potion extends GameObject{
    /**
     * The amount of healing provided by the game object.
     */
    protected int healAmount;

    /**
     * Constructs a new game object with the specified game panel.
     *
     * @param gamePanel The game panel.
     * @param worldX
     * @param worldY
     */
    public Potion(GamePanel gamePanel, int worldX, int worldY) {
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

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
}
