package mvc.game.state;

import mvc.game.controller.C_CollisionManager;
import mvc.game.controller.C_GamePanel;
import mvc.game.controller.C_KeyHandler;
import mvc.game.model.Direction;
import mvc.game.model.M_Entity;
import mvc.game.model.M_Player;
import mvc.game.view.PlayerRenderer;
import mvc.game.view.TileRenderer;
import org.game.entity.Entity;


import java.awt.*;

public class PlayState implements GameState{
    private final M_Player player;
    private final C_GamePanel gamePanel;
    private final TileRenderer tileRenderer;
    private final PlayerRenderer playerRenderer;
    private final C_CollisionManager collisionManager;


    public PlayState(C_GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.tileRenderer = new TileRenderer(gamePanel, gamePanel.getTileManager());
        this.playerRenderer = new PlayerRenderer(gamePanel, (M_Player) gamePanel.getPlayer());
        this.collisionManager = new C_CollisionManager(gamePanel);
        this.player = (M_Player) gamePanel.getPlayer();
    }
    @Override
    public void update() {
        handlePlayerMovement();
    }

    private void handlePlayerMovement() {
        C_KeyHandler keyHandler = gamePanel.getKeyHandler();
        /*
        if (keyHandler.upPressed && canMove(player.worldX, player.worldY - player.getSpeed())) {
            player.moveUp();
        }
        if (keyHandler.downPressed && canMove(player.worldX, player.worldY + player.getSpeed())) {
            player.moveDown();
        }
        if (keyHandler.leftPressed && canMove(player.worldX - player.getSpeed(), player.worldY)) {
            player.moveLeft();
        }
        if (keyHandler.rightPressed && canMove(player.worldX + player.getSpeed(), player.worldY)) {
            player.moveRight();
        }
         */

        if (keyHandler.upPressed) {
            player.direction = Direction.UP;
            collisionManager.checkTile(player);
            if (!player.collisionOn) {
                player.moveUp();
            }
            player.collisionOn = false;
        }
        if (keyHandler.downPressed) {
            player.direction = Direction.DOWN;
            collisionManager.checkTile(player);
            if (!player.collisionOn) {
                player.moveDown();
            }
            player.collisionOn = false;
        }
        if (keyHandler.leftPressed) {
            player.direction = Direction.LEFT;
            collisionManager.checkTile(player);
            if (!player.collisionOn) {
                player.moveLeft();
            }
            player.collisionOn = false;
        }
        if (keyHandler.rightPressed) {
            player.direction = Direction.RIGHT;
            collisionManager.checkTile(player);
            if (!player.collisionOn) {
                player.moveRight();
            }
            player.collisionOn = false;
        }


    }

    //private boolean canMove(int x, int y) {return collisionManager.canMoveTo(x, y);}

    @Override
    public void render(Graphics2D g2) {
        tileRenderer.render(g2);
        playerRenderer.render(g2);
    }


}
