package mvc.game.controller;

import mvc.game.model.*;
import mvc.game.model.tile.TileManager;
import mvc.game.state.GameState;
import mvc.game.state.TitleState;
import mvc.game.view.GameView;
import mvc.game.view.TileRenderer;
import org.game.entity.Entity;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class C_GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final static int ORIGINAL_TILE_SIZE = 16;
    final static int SCALE = 3;
    final static public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final static public int MAX_SCREEN_COL = 16;
    final static public int MAX_SCREEN_ROW  = 12;
    final static public int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    final public static int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;

    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;


    // FPS SETTINGS
    private static final int FPS = 60;


    // ENTITY
    public M_Entity player = new M_Player(this);
    public List<M_NPCs> NPCs = new ArrayList<M_NPCs>();


    // MANAGERS
    private final C_EntityManager entityManager = new C_EntityManager(this);
    public C_KeyHandler keyHandler = new C_KeyHandler(this);

    // VIEW
    private final TileManager tileManager = new TileManager(this);
    private final TileRenderer tileRenderer = new TileRenderer(this, tileManager);
    private final GameView gameView = new GameView(this, tileRenderer);

    // THREAD
    private Thread gameThread;

    // GAME STATE
    private GameState currentState;



    public C_GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH , SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        setCurrentState(new TitleState(this));
    }

    public void setupGame(){
        this.entityManager.setNPC();
    }


    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
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
        currentState.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //renderManager.render(g2, entityManager.getEntities());
        gameView.render(g2);
    }

    public C_KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setCurrentState(GameState state) {
        if (this.currentState != null) {
            //this.currentState.exit(this);
        }
        this.currentState = state;
        //this.currentState.enter(this);
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public TileManager getTileManager(){
        return tileManager;
    }

    public M_Player getPlayer(){
        return (M_Player) player;
    }
    public ArrayList<M_NPCs> getNpcs(){
        return (ArrayList<M_NPCs>) NPCs;
    }
}

