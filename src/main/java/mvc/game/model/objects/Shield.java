package mvc.game.model.objects;

import mvc.game.controller.GamePanel;
import mvc.game.model.entity.Player;

public class Shield extends GameObject{

    /**
     * The defense value of the game object.
     */
    public int defenseValue;

    /**
     * Constructs a new game object with the specified game panel.
     *
     * @param gamePanel The game panel.
     * @param worldX
     * @param worldY
     */
    public Shield(GamePanel gamePanel, int worldX, int worldY) {
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

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
}
