package org.game.main;

import org.game.entity.Player;
import org.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile => default for resolution fro everything
    final int scale = 3; // Scaling cause 16x16px looks small

    final public int tileSize = originalTileSize * scale; //48x48 tile
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth = tileSize * maxScreenCol; // 768px
    final public int screenHeight = tileSize * maxScreenRow; // 576px

    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Something I can start and stop
    Player player = new Player(this,keyH);

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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
