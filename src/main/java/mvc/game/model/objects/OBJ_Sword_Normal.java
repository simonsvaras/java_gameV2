package mvc.game.model.objects;

import mvc.game.model.EntityType;
import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Player;

public class OBJ_Sword_Normal extends GameObject{
    public OBJ_Sword_Normal(C_GamePanel gamePanel) {
        super(gamePanel);

        name = "Normal Sword";
        type = EntityType.SWORD;
        attackValue = 1;
        description = "[" + name + "]\nAn odl sword";

        attackArea.width = 40;
        attackArea.height = 40;

        loadPlayerSprites();
    }

    @Override
    public void loadPlayerSprites() {
        image = setup("/objects/sword_normal", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
    }


    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {

    }
}
