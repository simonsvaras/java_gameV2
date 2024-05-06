package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;


public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gamePanel) {
        super(gamePanel);
        name = "Boots";
        down1 = setup("/objects/boots");

    }
}
