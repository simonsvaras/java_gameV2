package org.game.main;


import org.game.entity.NPC_OldMan;
import org.game.monster.MON_GreenSlime;
import org.game.object.*;
import org.game.tile_interactive.IT_DryTree;

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
        gamePanel.obj[i] = new OBJ_Chest(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*47;
        gamePanel.obj[i].worldY = gamePanel.tileSize*2;
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
        gamePanel.obj[i] = new OBJ_Potion_Red(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*30;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Shield_Blue(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*28;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;
        i++;
        gamePanel.obj[i] = new OBJ_Heart(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*29;
        gamePanel.obj[i].worldY = gamePanel.tileSize*19;

        i++;
        gamePanel.obj[i] = new OBJ_Door(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*41;
        gamePanel.obj[i].worldY = gamePanel.tileSize*25;

        i++;
        gamePanel.obj[i] = new OBJ_Boots(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*47;
        gamePanel.obj[i].worldY = gamePanel.tileSize*25;

        i++;
        gamePanel.obj[i] = new OBJ_Key(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*45;
        gamePanel.obj[i].worldY = gamePanel.tileSize*22;

        i++;
        gamePanel.obj[i] = new OBJ_Key(gamePanel);
        gamePanel.obj[i].worldX = gamePanel.tileSize*46;
        gamePanel.obj[i].worldY = gamePanel.tileSize*22;
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
        int i = 0;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*4;
        gamePanel.monster[i].worldY = gamePanel.tileSize*30;

        i++;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*5;
        gamePanel.monster[i].worldY = gamePanel.tileSize*30;

        i++;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*6;
        gamePanel.monster[i].worldY = gamePanel.tileSize*30;

        i++;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*4;
        gamePanel.monster[i].worldY = gamePanel.tileSize*32;

        i++;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*5;
        gamePanel.monster[i].worldY = gamePanel.tileSize*32;

        i++;
        gamePanel.monster[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*6;
        gamePanel.monster[i].worldY = gamePanel.tileSize*32;
    }

    public void setInteractiveTile (){
        int i = 0;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 33, 19);
        i++;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 33, 20);
        i++;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 8, 29);
        i++;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 8, 30);
        i++;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 8, 31);
        i++;
        gamePanel.interactiveTile[i] = new IT_DryTree(gamePanel, 8, 32);

    }
}
