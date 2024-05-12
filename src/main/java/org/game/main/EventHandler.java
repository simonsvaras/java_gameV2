package org.game.main;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
   EventRect eventRec[][];
    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // Have event rectangle on every single tile on the map
        eventRec = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
            // TRIGGER POINT OF TILE
            eventRec[col][row] = new EventRect();
            eventRec[col][row].x = 23;
            eventRec[col][row].y = 23;
            eventRec[col][row].width = 2;
            eventRec[col][row].height = 2;

            eventRec[col][row].eventRectDefaultX = eventRec[col][row].x;
            eventRec[col][row].eventRectDefaultY = eventRec[col][row].y;

            col++;
            if(col == gamePanel.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent(){
        if(hit(22,20,"right")){
            damagePit(gamePanel.dialogueState);
        }
    }

    public boolean hit (int col, int row, String reqDirection){
        boolean hit = false;

        // Getting players current solid area position
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        // Getting rectangle solid area position
        eventRec[col][row].x = col *  gamePanel.tileSize + eventRec[col][row].x;
        eventRec[col][row].y = row * gamePanel.tileSize + eventRec[col][row].y;

        if(gamePanel.player.solidArea.intersects(eventRec[col][row])){
            if(gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        // After checking reset solid area
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRec[col][row].x = eventRec[col][row].eventRectDefaultX;
        eventRec[col][row].y = eventRec[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gamePanel.gameState = gameState;
        gamePanel.playSE(6);
        gamePanel.ui.currentDialogue = "You fall into a pit";

        gamePanel.player.life -= 1;
    }

    public void healingPool(int gameState){
        if(gamePanel.keyH.enterPressed == true){
            gamePanel.gameState = gameState;
            gamePanel.player.attackCanceled = true;
            gamePanel.ui.currentDialogue = "Your life has been recovered";
        }
    }

}
