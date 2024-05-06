package org.game.main;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
    Rectangle eventRec;
    int eventRecDefaultX, eventRecDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // TRIGGER POINT OF TILE
        eventRec = new Rectangle();
        eventRec.x = 23;
        eventRec.y = 23;
        eventRec.width = 2;
        eventRec.height = 2;

        eventRecDefaultX = eventRec.x;
        eventRecDefaultY = eventRec.y;
    }

    public void checkEvent(){
        if(hit(22,20,"right")){
            damagePit(gamePanel.dialogueState);
        }
    }

    public boolean hit (int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        // Getting players current solid area position
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        // Getting rectangle solid area position
        eventRec.x = eventCol *  gamePanel.tileSize + eventRec.x;
        eventRec.y = eventRow * gamePanel.tileSize + eventRec.y;

        if(gamePanel.player.solidArea.intersects(eventRec)){
            if(gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        // After checking reset solid area
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRec.x = eventRecDefaultX;
        eventRec.y = eventRecDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You fall into a pit";

        gamePanel.player.life -= 1;
    }

    public void healingPool(int gameState){
        if(gamePanel.keyH.enterPressed == true){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Your life has been recovered";
        }
    }

}
