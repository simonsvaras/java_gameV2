package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gamePanel;
    public OBJ_Potion_Red(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Red potion";
        type = type_consumable;
        value = 5;
        stackable = true;

        down1 = setup("/objects/potion_red", gamePanel.tileSize,gamePanel.tileSize);
        description = "[Red Potion]\nHeals your life by" + value + ".";

    }

    public void use(Entity entity){
        gamePanel.gameState = gamePanel.dialogueState;

        gamePanel.ui.currentDialogue = "You drink the " + name + "!\n"
                + "Your life has been recovered by " + value + ".";

        entity.life += value;

        gamePanel.playSE(2);

    }
}
