package org.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile => default for resolution fro everything
    final int scale = 3; // Scaling cause 16x16px looks small
    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Something I can start and stop

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Is used to create a thread => because we used Runnable
    // When we start Thread object it will automatic run this method
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; //0,0166 seconds refresh rate
        double nextDrawTime = System.nanoTime() + drawInterval;
        int drawCount = 0;

        //Making game loop witch will be core of our game
        while (gameThread != null){

            // 1 UPDATE: update information such as character positions
            this.update();
            // 2 DRAW: draw the screen with the updated information
            this.repaint(); //By this we call paintComponent


            // SLEEP -- 60FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(keyH.upPressed) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed){
            playerY += playerSpeed;
        } else if (keyH.leftPressed){
            playerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY, tileSize, tileSize);

        g2.dispose();
    }
}
