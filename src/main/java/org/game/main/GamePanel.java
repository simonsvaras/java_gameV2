package org.game.main;

import org.game.entity.Player;
import org.game.object.SuperObject;
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

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound soundEF = new Sound();
    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter  assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject[] obj = new  SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
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

        // TILE
        tileManager.draw(g2);

        // OBJECT
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    // SOUND EFFECT
    public void playSE(int i){
        soundEF.setFile(i);
        soundEF.play();
    }
}
