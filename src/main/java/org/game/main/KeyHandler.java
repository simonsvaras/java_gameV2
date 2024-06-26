package org.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITTLE STATE
        if(gamePanel.gameState == gamePanel.tittleState){
            tittleState(code);
        }

        // PLAY STATE
        else if(gamePanel.gameState == gamePanel.playState) {
            playState(code);
        }
        // PAUSE STATE
        else if(gamePanel.gameState == gamePanel.pauseState){
            pauseState(code);
        }

        // DIALOGUE STATE
        else if(gamePanel.gameState == gamePanel.dialogueState){
            dialogueState(code);
        }

        // CHARACTER STATE
        else if(gamePanel.gameState == gamePanel.characterState){
            characterState(code);
        }

        // OPTION STATE
        else if(gamePanel.gameState == gamePanel.optionState){
            optionState(code);
        }
    }

    public void  tittleState(int code){
        if(code == KeyEvent.VK_W ){
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0){
                gamePanel.ui.commandNum = 2;                }
        }
        if(code == KeyEvent.VK_S ){
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > 2){
                gamePanel.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gamePanel.ui.commandNum == 0){
                gamePanel.gameState = gamePanel.playState;
                gamePanel.playMusic(0);
            }
            if(gamePanel.ui.commandNum == 1){
                //TODO
            }
            if (gamePanel.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.pauseState;
        }
        if(code == KeyEvent.VK_C){
            gamePanel.gameState = gamePanel.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.gameState = gamePanel.optionState;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gamePanel.gameState = gamePanel.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gamePanel.ui.slotRow != 0) {
                gamePanel.ui.slotRow--;
                gamePanel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.slotCol != 0) {
                gamePanel.ui.slotCol--;
                gamePanel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gamePanel.ui.slotRow != 3) {
                gamePanel.ui.slotRow++;
                gamePanel.playSE(8);
            }}
        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.slotCol != 4) {
                gamePanel.ui.slotCol++;
                gamePanel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gamePanel.player.selectItems();
        }
    }
    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.gameState = gamePanel.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gamePanel.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W){
            gamePanel.ui.commandNum--;
            gamePanel.playSE(8);
            if(gamePanel.ui.commandNum < 0){
                gamePanel.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gamePanel.ui.commandNum++;
            gamePanel.playSE(8);
            if(gamePanel.ui.commandNum > maxCommandNum){
                gamePanel.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.subState == 0){
                if(gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale > 0){
                    gamePanel.music.volumeScale--;
                    gamePanel.music.checkVolume();
                    gamePanel.playSE(9);
                    gamePanel.music.loadSound();
                }

                if(gamePanel.ui.commandNum == 2 && gamePanel.soundEF.volumeScale > 0){
                    gamePanel.soundEF.volumeScale--;
                    gamePanel.soundEF.checkVolume();
                    gamePanel.playSE(9);
                    gamePanel.soundEF.loadSound();
                }

            }


        }

        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.subState == 0){
                if(gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale < 5){
                    gamePanel.music.volumeScale++;
                    gamePanel.music.checkVolume();
                    gamePanel.playSE(9);
                    gamePanel.music.loadSound();
                }

                if(gamePanel.ui.commandNum == 2 && gamePanel.soundEF.volumeScale < 5){
                    gamePanel.soundEF.volumeScale++;
                    gamePanel.soundEF.checkVolume();
                    gamePanel.playSE(9);
                    gamePanel.soundEF.loadSound();
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
