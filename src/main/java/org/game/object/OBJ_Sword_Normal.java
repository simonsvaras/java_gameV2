package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);
        name = "Normal Sword";
        type = type_sword;
        down1 = setup("/objects/sword_normal", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn odl sword";

        attackArea.width = 40;
        attackArea.height = 40;
    }
}
