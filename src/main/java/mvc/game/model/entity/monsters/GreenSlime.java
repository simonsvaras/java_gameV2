package mvc.game.model.entity.monsters;

import mvc.game.controller.GamePanel;
import mvc.game.model.Direction;
import mvc.game.model.EntityType;
import mvc.game.model.entity.NPCs;

public class GreenSlime extends NPCs {
    public GreenSlime(GamePanel gamePanel) {
        super(gamePanel);


        setDefaultValues();
        loadSprites();
    }

    private void setDefaultValues(){
        // Starting position
        position.setX(GamePanel.TILE_SIZE * 23);
        position.setY(GamePanel.TILE_SIZE * 17);

        // direction for sprites
        direction = Direction.DOWN;

        type = EntityType.MONSTER;
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
        sprite.loadSprite("up", setup("/monster/greenslime_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("down", setup("/monster/greenslime_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("left", setup("/monster/greenslime_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        sprite.loadSprite("right", setup("/monster/greenslime_down_1", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), setup("/monster/greenslime_down_2", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
    }
}
