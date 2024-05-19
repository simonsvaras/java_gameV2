package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {

    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);

        image = setup("/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/objects/heart_half", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/objects/heart_blank", gamePanel.tileSize, gamePanel.tileSize);

    }

    public void use(Entity entity){
        gamePanel.playSE(2);
        gamePanel.ui.addMessage("Life " + value);
        entity.life += value;

    }
}
