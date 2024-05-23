package mvc.game.controller;

import mvc.game.model.M_Entity;
import mvc.game.model.M_NPC_OldMan;
import mvc.game.model.objects.GameObject;

import java.util.ArrayList;

public class C_EntityManager {
    C_GamePanel gamePanel;
    public C_EntityManager(C_GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void setNPC(){
        M_NPC_OldMan oldMan;
        oldMan = new M_NPC_OldMan(gamePanel);
        gamePanel.NPCs.add(oldMan);

        oldMan = new M_NPC_OldMan(gamePanel);
        gamePanel.NPCs.add(oldMan);

        oldMan = new M_NPC_OldMan(gamePanel);
        gamePanel.NPCs.add(oldMan);

    }


}
