package mvc.game.state;

import mvc.game.controller.C_CollisionManager;
import mvc.game.controller.C_GamePanel;
import mvc.game.controller.C_KeyHandler;
import mvc.game.model.*;
import mvc.game.view.NPCRenderer;
import mvc.game.view.PlayerRenderer;
import mvc.game.view.TileRenderer;


import java.awt.*;
import java.util.List;
import java.util.Random;

public class PlayState implements GameState{

    private final C_GamePanel gamePanel;
    private final TileRenderer tileRenderer;
    private final PlayerRenderer playerRenderer;
    private final C_CollisionManager collisionManager;
    private final NPCRenderer npcRenderer;
    private final C_KeyHandler keyHandler;

    private Random random = new Random();




    public PlayState(C_GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.keyHandler = gamePanel.getKeyHandler();
        this.tileRenderer = new TileRenderer(gamePanel, gamePanel.getTileManager());
        this.playerRenderer = new PlayerRenderer(gamePanel, (M_Player) gamePanel.getPlayer());
        this.npcRenderer = new NPCRenderer(gamePanel);
        this.collisionManager = new C_CollisionManager(gamePanel);
    }
    @Override
    public void update() {
        // PLAYERS MOVEMENT
        handleMovement(gamePanel.getPlayer());


        for (M_NPCs NPC : gamePanel.getNpcs()) {
            handlerNPCMovement(NPC);
        }
    }

    private void handlerNPCMovement(M_NPCs object) {
        Direction previousDirection = object.direction;

        object.actionLockCounter++;
        if (object.actionLockCounter > object.getMaxActionLockCounter()) {
            changeDirectionRandomly(object);
            object.actionLockCounter = 0; // Reset počítadla po změně směru
        }

        tryMove(object, object.direction);


        changeMovementFrame(object, previousDirection);

    }

    private void changeDirectionRandomly(M_NPCs object) {
        int directionChoice = random.nextInt(4); // Generuje číslo mezi 0 a 3

        switch (directionChoice) {
            case 0:
                object.direction = Direction.UP;
                break;
            case 1:
                object.direction = Direction.DOWN;
                break;
            case 2:
                object.direction = Direction.LEFT;
                break;
            case 3:
                object.direction = Direction.RIGHT;
                break;
        }
    }

    private void handleMovement(LiveObjects object) {

        boolean hasMoved = false;

        Direction previousDirection = object.direction;

        if (keyHandler.upPressed) {
            object.direction = Direction.UP;

            hasMoved = tryMove(object, object.direction);
            object.collisionOn = false;
        }

        if (keyHandler.downPressed) {
            object.direction = Direction.DOWN;
            hasMoved = tryMove(object, object.direction);
            object.collisionOn = false;
        }

        if (keyHandler.leftPressed) {
            object.direction = Direction.LEFT;
            hasMoved = tryMove(object, object.direction);
            object.collisionOn = false;
        }

        if (keyHandler.rightPressed) {
            object.direction = Direction.RIGHT;
            hasMoved = tryMove(object, object.direction);
            object.collisionOn = false;
        }

        if (hasMoved) {
            changeMovementFrame(object, previousDirection);
        }

    }



    private void changeMovementFrame(LiveObjects object, Direction previousDirection){
        if (object.direction != previousDirection) {
            object.resetAnimation(); // Reset animation if direction changes
        }
        object.updateMovementFrame();
    }



    private boolean tryMove(LiveObjects object, Direction direction){
        if (!collisionManager.checkTileCollision(object, direction)) {
            switch (direction) {
                case UP:
                    object.moveUp();
                    break;
                case DOWN:
                    object.moveDown();
                    break;
                case LEFT:
                    object.moveLeft();
                    break;
                case RIGHT:
                    object.moveRight();
                    break;
            }
            return true;
        }
        return false;
    }




    @Override
    public void render(Graphics2D g2) {
        tileRenderer.render(g2);
        playerRenderer.render(g2);
        npcRenderer.render(g2);
    }

}
