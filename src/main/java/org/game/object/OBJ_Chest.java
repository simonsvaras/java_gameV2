package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        down1 = setup("/objects/chest", gamePanel.tileSize, gamePanel.tileSize);

    }
}
