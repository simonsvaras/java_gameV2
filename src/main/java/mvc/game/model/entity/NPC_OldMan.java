package mvc.game.model.entity;

import mvc.game.controller.GamePanel;
import mvc.game.model.Direction;

public class NPC_OldMan extends NPCs {

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        loadPlayerSprites();
    }

    public void setDefaultValues() {
        // Starting position
        position.setX(GamePanel.TILE_SIZE * 23);
        position.setY(GamePanel.TILE_SIZE * 19);
        // speed
        speed = 1;
        // direction for sprites
        direction = Direction.DOWN;
    }


    @Override
    public void loadPlayerSprites(){
        sprite.loadSprite("up", setup("/NPC/oldman_up_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/NPC/oldman_up_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/NPC/oldman_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/NPC/oldman_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/NPC/oldman_left_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/NPC/oldman_left_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/NPC/oldman_right_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/NPC/oldman_right_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
    }

}