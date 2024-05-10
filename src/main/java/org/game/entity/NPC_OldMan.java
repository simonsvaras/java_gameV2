package org.game.entity;

import org.game.main.GamePanel;

import java.util.Random;


public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        // SETUP SPRITES
        up1 = setup("/NPC/oldman_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/NPC/oldman_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1= setup("/NPC/oldman_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/NPC/oldman_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/NPC/oldman_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/NPC/oldman_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/NPC/oldman_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/NPC/oldman_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number form 1 to 100

            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void setDialogue(){
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to island to \n find some treasure?";
        dialogues[2] = "I have been here for too long";
        dialogues[3] = "It is impossible to find the \n way out";
        dialogues[4] = "Sounds like your problem";

    }

    public void speak(){

        // Here we can do character specific stat
        super.speak();
    }


}
