package org.game.tile_interactive;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gamePanel;
    public boolean destructible = false;
    public InteractiveTile(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE(){}
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }
    public void update(){

    }
}
