package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

    GamePanel gamePanel;
    public OBJ_Coin_Bronze(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_pickupOnly;
        down1 = setup("/objects/coin_bronze", gamePanel.tileSize, gamePanel.tileSize);

        value = 1;
    }

    public void use(Entity entity){
        gamePanel.playSE(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
    }
}
