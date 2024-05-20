package org.game.tile_interactive;

import org.game.entity.Entity;
import org.game.main.GamePanel;

public class IT_DryTree extends InteractiveTile{
    GamePanel gamePanel;
    public IT_DryTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;

        down1 = setup("/tiles_interactive/drytree", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;

    }

    public boolean isCorrectItem(Entity entity){
        return entity.currentWeapon.type == type_axe;
    }

    public void playSE(){
        gamePanel.playSE(9);
    }
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Trunk(gamePanel, worldX/gamePanel.tileSize, worldY/gamePanel.tileSize);
        return tile;
    }
}
