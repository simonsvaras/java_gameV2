package mvc.game.model.entity.monsters;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.entity.M_NPCs;

public class M_GreenSlime extends M_NPCs {
    public M_GreenSlime(C_GamePanel gamePanel) {
        super(gamePanel);


        setDefaultValues();
        loadSprites();
    }

    private void setDefaultValues(){
        // Starting position
        worldX = C_GamePanel.TILE_SIZE * 23;
        worldY = C_GamePanel.TILE_SIZE * 17;

        // direction for sprites
        direction = Direction.DOWN;

        type = TYPE_MONSTER;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }


    private void loadSprites(){
        sprite.loadSprite("up", setup("/monster/greenslime_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/monster/greenslime_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/monster/greenslime_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/monster/greenslime_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
    }
}
