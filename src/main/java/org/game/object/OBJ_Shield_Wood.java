package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        name = "Wood Shield";
        type = type_shield;
        down1 = setup("/objects/shield_wood", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;

        description = "[" + name + "]\nIs made by wood";

    }
}
