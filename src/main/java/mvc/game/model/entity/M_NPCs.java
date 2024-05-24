package mvc.game.model.entity;

import mvc.game.controller.C_GamePanel;

public class M_NPCs extends LiveObjects {
    public int actionLockCounter;
    private int maxActionLockCounter;

    public M_NPCs(C_GamePanel gamePanel) {
        super(gamePanel);
        maxActionLockCounter = 120;
    }

    public int getMaxActionLockCounter(){
        return maxActionLockCounter;
    }
}