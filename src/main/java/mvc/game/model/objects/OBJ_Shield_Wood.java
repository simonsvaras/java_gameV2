package mvc.game.model.objects;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.M_Player;


import java.awt.*;

public class OBJ_Shield_Wood extends GameObject{
    public OBJ_Shield_Wood(C_GamePanel gamePanel) {
        super(gamePanel);

        name = "Wood Shield";
        type = TYPE_SHIELD;
        image = setup("/objects/shield_wood", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE);
        defenseValue = 1;

        description = "[" + name + "]\nIs made by wood";

    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }




    @Override
    public void interact(M_Player player) {

    }

    @Override
    public void pickUp(M_Player player) {

    }
}
