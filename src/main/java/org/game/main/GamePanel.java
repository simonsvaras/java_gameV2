package org.game.main;

import org.game.entity.Entity;
import org.game.entity.Player;
import org.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public KeyHandler keyH = new KeyHandler(this);
    Sound soundEF = new Sound();
    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter  assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity[] obj = new  Entity[10];
    public Entity[] npc = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int tittleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        gameState = tittleState;
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
        if(gameState == playState){
            // PLAYER
            player.update();

            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if(gameState == pauseState){
            // nothing
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITTLE SCREEN
        if(gameState == tittleState){
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            tileManager.draw(g2);

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (Entity value : npc) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    return Integer.compare(o1.worldY, o2.worldY);
                }
            });

            // DRAW RESULT
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            // CLEAR ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);

            // DEBUG
            if (keyH.checkDrawTime == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: +" + passed, 10, 400);
                System.out.println("Draw Time: +" + passed);
            }

            g2.dispose();
        }


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
