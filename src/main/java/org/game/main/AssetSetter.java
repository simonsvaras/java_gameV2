package org.game.main;


import org.game.entity.NPC_OldMan;
import org.game.monster.MON_GreenSlime;
import org.game.object.*;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        int i = 0;
        gamePanel.obj[i] = new OBJ_Chest(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*21;
        gamePanel.obj[i].worldY = gamePanel.tileSize*22;
        i++;
        gamePanel.obj[i] = new OBJ_Coin_Bronze(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*23;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Coin_Bronze(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*24;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Coin_Bronze(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*25;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Axe(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*26;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Potion_Red(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*27;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Shield_Blue(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*28;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
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
