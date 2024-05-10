package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;


public class OBJ_Door extends Entity {


    public OBJ_Door(GamePanel gamePanel) {
        super(gamePanel);
        name = "Door";

        down1 = setup("/objects/door", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;

    }
}
