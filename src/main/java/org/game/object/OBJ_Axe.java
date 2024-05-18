package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        name = "Axe";
        type = type_axe;
        down1 = setup("/objects/axe", gamePanel.tileSize, gamePanel.tileSize);

        attackValue = 2;

        attackArea.width = 30;
        attackArea.height = 30;

       description = "[" + name + "]\nCan cut a trees";
    }
}
