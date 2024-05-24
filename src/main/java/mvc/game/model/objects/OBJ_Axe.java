package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.M_Player;

public class OBJ_Axe extends GameObject{
    public OBJ_Axe(C_GamePanel gamePanel) {
        super(gamePanel);
        name = "Axe";
        type = TYPE_AXE;
        image = setup("/objects/axe", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);

        attackValue = 2;

        attackArea.width = 30;
        attackArea.height = 30;

        description = "[" + name + "]\nCan cut a trees";
    }

    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {
        player.getInventory().addItem(this);
    }




}
