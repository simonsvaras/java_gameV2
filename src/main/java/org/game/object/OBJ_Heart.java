package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";

        image = setup("/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/objects/heart_half", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/objects/heart_blank", gamePanel.tileSize, gamePanel.tileSize);

    }
}
