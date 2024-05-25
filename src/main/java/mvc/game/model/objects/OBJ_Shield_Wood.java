package mvc.game.model.objects;

import mvc.game.model.EntityType;
import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Player;

public class OBJ_Shield_Wood extends GameObject{
    public OBJ_Shield_Wood(C_GamePanel gamePanel) {
        super(gamePanel);

        name = "Wood Shield";
        type = EntityType.SHIELD;
        defenseValue = 1;

        description = "[" + name + "]\nIs made by wood";

    }

    @Override
    public void loadPlayerSprites() {
        image = setup("/objects/shield_wood", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);

    }


    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {

    }
}
