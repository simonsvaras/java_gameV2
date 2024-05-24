package mvc.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles keyboard input for the game.
 */
public class C_KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    private Map<Integer, Runnable> keyPressedActions = new HashMap<>();
    private Map<Integer, Runnable> keyReleasedActions = new HashMap<>();
    private C_GamePanel gamePanel;

    /**
     * Constructs a key handler for the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public C_KeyHandler(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initializeKeyMappings();
    }

    /**
     * Initializes the key mappings for key pressed and key released actions.
     */
    private void initializeKeyMappings() {
        // Actions on key press
        keyPressedActions.put(KeyEvent.VK_W, () -> upPressed = true);
        keyPressedActions.put(KeyEvent.VK_S, () -> downPressed = true);
        keyPressedActions.put(KeyEvent.VK_A, () -> leftPressed = true);
        keyPressedActions.put(KeyEvent.VK_D, () -> rightPressed = true);
        keyPressedActions.put(KeyEvent.VK_ENTER, () -> enterPressed = true);

        // Actions on key release
        keyReleasedActions.put(KeyEvent.VK_W, () -> upPressed = false);
        keyReleasedActions.put(KeyEvent.VK_S, () -> downPressed = false);
        keyReleasedActions.put(KeyEvent.VK_A, () -> leftPressed = false);
        keyReleasedActions.put(KeyEvent.VK_D, () -> rightPressed = false);
        keyReleasedActions.put(KeyEvent.VK_ENTER, () -> enterPressed = false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed for keyTyped event
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Runnable action = keyPressedActions.get(e.getKeyCode());
        if (action != null) {
            action.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Runnable action = keyReleasedActions.get(e.getKeyCode());
        if (action != null) {
            action.run();
        }
    }

    /**
     * Resets all key pressed states to false.
     */
    public void reset() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        enterPressed = false;
    }
}