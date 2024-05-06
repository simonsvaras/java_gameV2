package org.game.main;


import org.game.entity.NPC_OldMan;
import org.game.monster.MON_GreenSlime;
import org.game.object.OBJ_Boots;
import org.game.object.OBJ_Chest;
import org.game.object.OBJ_Door;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new OBJ_Chest(gamePanel);
        gamePanel.obj[0].worldX = gamePanel.tileSize*21;
        gamePanel.obj[0].worldY = gamePanel.tileSize*22;
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize*21;
        gamePanel.npc[0].worldY = gamePanel.tileSize*21;

        gamePanel.npc[1] = new NPC_OldMan(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize*25;
        gamePanel.npc[1].worldY = gamePanel.tileSize*21;
    }

    public void setMonster(){
        gamePanel.monster[0] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize*21;
        gamePanel.monster[0].worldY = gamePanel.tileSize*19;
    }
}
