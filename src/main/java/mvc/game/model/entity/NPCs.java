package mvc.game.model.entity;

import mvc.game.model.EntityType;
import mvc.game.controller.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class NPCs extends LiveObjects {
    private int actionLockCounter;
    private int maxActionLockCounter;

    /**
     * The dialogues for the object.
     */
    protected List<String> dialogues = new ArrayList<>();

    public NPCs(GamePanel gamePanel) {
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