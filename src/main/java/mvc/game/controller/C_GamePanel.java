package mvc.game.controller;

import mvc.game.model.M_Player;
import mvc.game.state.GameState;
import mvc.game.state.TitleState;
import org.game.entity.Player;
import org.game.main.UI;

import javax.swing.*;
import java.awt.*;

public class C_GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    final public int tileSize = originalTileSize * scale;
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth = tileSize * maxScreenCol;
    final public int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    // FPS SETTINGS
    int FPS = 60;

    // MANAGERS
    C_EntityManager entityManager = new C_EntityManager();
    C_RenderManager renderManager = new C_RenderManager();
    public C_KeyHandler keyHandler = new C_KeyHandler(this);  // Assuming existing KeyHandler class



    // ENTITY
    public M_Player player = new M_Player(this);

    // THREAD
    Thread gameThread;

    // GAME STATE
    private GameState currentState;

    /*
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;

     */

    public C_GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        setCurrentState(new TitleState());
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; //0,0166 seconds refresh rate
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Making game loop witch will be core of our game
        while (gameThread != null) {
            // 1 UPDATE: update information such as character positions
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint();//By this we call paintComponent

            // SLEEP -- 60FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        currentState.update(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //renderManager.render(g2, entityManager.getEntities());
        currentState.render(g2);
    }

    public C_KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}
