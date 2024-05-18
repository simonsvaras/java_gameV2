package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Shield_Blue extends Entity {
    public OBJ_Shield_Blue(GamePanel gamePanel) {
        super(gamePanel);


        name = "Blue shield";
        type = type_shield;
        down1 = setup("/objects/shield_blue", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 2;

        description = "[" + name + "]\nIs made by wood";
    }
}
