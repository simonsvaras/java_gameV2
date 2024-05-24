package mvc.game.controller;

import mvc.game.model.entity.M_Entity;
import mvc.game.model.entity.M_NPCs;
import mvc.game.model.entity.M_Player;
import mvc.game.model.tile.TileManager;
import mvc.game.state.GameState;
import mvc.game.state.TitleState;
import mvc.game.view.GameView;
import mvc.game.view.TileRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main game panel where the game is rendered and updated.
 */
public class C_GamePanel extends JPanel implements Runnable {
    private static final Logger logger = LogManager.getLogger(C_GamePanel.class);



    // SCREEN SETTINGS
    /**
     * The original size of a tile.
     */
    final static int ORIGINAL_TILE_SIZE = 16;

    /**
     * The scale factor for the tiles.
     */
    final static int SCALE = 3;

    /**
     * The size of a tile after scaling.
     */
    final static public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    /**
     * The maximum number of columns on the screen.
     */
    final static public int MAX_SCREEN_COL = 16;

    /**
     * The maximum number of rows on the screen.
     */
    final static public int MAX_SCREEN_ROW  = 12;

    /**
     * The height of the screen in pixels.
     */
    final static public int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    /**
     * The width of the screen in pixels.
     */
    final public static int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;

    // WORLD SETTINGS
    /**
     * The maximum number of columns in the world.
     */
    public static final int MAX_WORLD_COL = 50;

    /**
     * The maximum number of rows in the world.
     */
    public static final int MAX_WORLD_ROW = 50;

    // FPS SETTINGS
    private static final int FPS = 60;

    // ENTITY
    /**
     * The player entity.
     */
    public M_Entity player = new M_Player(this);

    /**
     * The list of NPCs in the game.
     */
    public List<M_NPCs> NPCs = new ArrayList<>();

    // MANAGERS
    private final C_EntityManager entityManager = new C_EntityManager(this);

    /**
     * The key handler for managing input.
     */
    public C_KeyHandler keyHandler = new C_KeyHandler(this);

    // VIEW
    private final TileManager tileManager = new TileManager(this);
    private final TileRenderer tileRenderer = new TileRenderer(this, tileManager);
    private final GameView gameView = new GameView(this, tileRenderer);

    // THREAD
    private Thread gameThread;

    // GAME STATE
    private GameState currentState;

    /**
     * Constructs a new C_GamePanel.
     */
    public C_GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH , SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        setCurrentState(new TitleState(this));
        logger.info("Game Panel initialized.");
    }

    /**
     * Sets up the game by initializing entities.
     */
    public void setupGame(){
        this.entityManager.setNPC();
        logger.debug("Game setup: NPCs initialized.");
    }

    /**
     * Starts the game by starting the game thread.
     */
    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
        logger.info("Game thread started.");
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.0166 seconds refresh rate
        double nextDrawTime = System.nanoTime() + drawInterval;
        logger.debug("Game loop started.");

        // Making game loop which will be core of our game
        while (gameThread != null) {
            // 1 UPDATE: update information such as character positions
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint(); // By this we call paintComponent

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
                logger.error("Game loop interrupted", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
        currentState.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Render the game view
        gameView.render(g2);
    }

    /**
     * Returns the key handler.
     *
     * @return the key handler
     */
    public C_KeyHandler getKeyHandler() {
        return keyHandler;
    }

    /**
     * Sets the current game state.
     *
     * @param state the new game state
     */
    public void setCurrentState(GameState state) {
        if (this.currentState != null) {
            logger.trace("Exiting state: {}", currentState.getClass().getSimpleName());
        }
        this.currentState = state;
        logger.trace("Entering state: {}", state.getClass().getSimpleName());
    }

    /**
     * Returns the current game state.
     *
     * @return the current game state
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * Returns the tile manager.
     *
     * @return the tile manager
     */
    public TileManager getTileManager(){
        return tileManager;
    }

    /**
     * Returns the player entity.
     *
     * @return the player entity
     */
    public M_Player getPlayer(){
        return (M_Player) player;
    }

    /**
     * Returns the list of NPCs.
     *
     * @return the list of NPCs
     */
    public ArrayList<M_NPCs> getNpcs(){
        return (ArrayList<M_NPCs>) NPCs;
    }
}
