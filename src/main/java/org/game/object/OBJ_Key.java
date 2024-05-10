package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";

        down1 = setup("/objects/key", gamePanel.tileSize, gamePanel.tileSize);

    }
}

