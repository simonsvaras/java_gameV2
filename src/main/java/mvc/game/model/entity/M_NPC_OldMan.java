package mvc.game.model.entity;

import mvc.game.controller.C_GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.objects.OBJ_Shield_Wood;
import mvc.game.model.objects.OBJ_Sword_Normal;

import java.util.Random;

public class M_NPC_OldMan extends M_NPCs{

    public M_NPC_OldMan(C_GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        loadPlayerSprites();
    }

    public void setDefaultValues() {
        // Starting position
        worldX = C_GamePanel.TILE_SIZE * 23;
        worldY = C_GamePanel.TILE_SIZE * 19;
        // speed
        speed = 1;
        // direction for sprites
        direction = Direction.DOWN;
    }


    @Override
    public void loadPlayerSprites(){
        sprite.loadSprite("up", setup("/NPC/oldman_up_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/NPC/oldman_up_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/NPC/oldman_down_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/NPC/oldman_down_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/NPC/oldman_left_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/NPC/oldman_left_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/NPC/oldman_right_1", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE), setup("/NPC/oldman_right_2", C_GamePanel.TILE_SIZE, C_GamePanel.TILE_SIZE));
    }

}