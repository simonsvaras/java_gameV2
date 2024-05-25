package mvc.game.model.objects;

import mvc.game.model.EntityType;
import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Player;

public class OBJ_Potion_Red extends GameObject{
    public OBJ_Potion_Red(C_GamePanel gamePanel) {
        super(gamePanel);

        name = "Red Potion";
        healAmount = 5;
        stackable = true;
        type = EntityType.CONSUMABLE;

        description = "[Red Potion]\nHeals your life by " + healAmount + ".";

        loadPlayerSprites();
    }

    @Override
    public void loadPlayerSprites() {
        image = setup("/objects/potion_red", C_GamePanel.TILE_SIZE,C_GamePanel.TILE_SIZE);

    }

    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {

    }



    /*
    @Override
    public void interact(M_Player player) {
        player.heal(healAmount);
        gamePanel.playSE(2);
        gamePanel.removeEntity(this);  // Předpokládáme, že existuje metoda pro odstranění objektu
    }

    @Override
    public void pickUp(M_Player player) {
        player.getInventory().addItem(this);
    }

     */
}
