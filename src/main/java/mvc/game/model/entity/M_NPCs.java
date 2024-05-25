package mvc.game.model.entity;

import mvc.game.model.EntityType;
import mvc.game.controller.C_GamePanel;

public class M_NPCs extends LiveObjects {
    private int actionLockCounter;
    private int maxActionLockCounter;

    public M_NPCs(C_GamePanel gamePanel) {
        super(gamePanel);
        maxActionLockCounter = 120;
        type = EntityType.NPC;
    }

    public int getMaxActionLockCounter(){
        return maxActionLockCounter;
    }

    public void setMaxActionLockCounter(int i) {
        maxActionLockCounter = i;
    }

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }
}