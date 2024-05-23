package mvc.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class C_KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    private Map<Integer, Runnable> keyPressedActions = new HashMap<>();
    private Map<Integer, Runnable> keyReleasedActions = new HashMap<>();
    private C_GamePanel gamePanel;

    public C_KeyHandler(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initializeKeyMappings();
    }

    private void initializeKeyMappings() {
        // Akce při stisku kláves
        keyPressedActions.put(KeyEvent.VK_W, () -> upPressed = true);
        keyPressedActions.put(KeyEvent.VK_S, () -> downPressed = true);
        keyPressedActions.put(KeyEvent.VK_A, () -> leftPressed = true);
        keyPressedActions.put(KeyEvent.VK_D, () -> rightPressed = true);
        keyPressedActions.put(KeyEvent.VK_ENTER, () -> enterPressed = true);

        // Akce při uvolnění kláves
        keyReleasedActions.put(KeyEvent.VK_W, () -> upPressed = false);
        keyReleasedActions.put(KeyEvent.VK_S, () -> downPressed = false);
        keyReleasedActions.put(KeyEvent.VK_A, () -> leftPressed = false);
        keyReleasedActions.put(KeyEvent.VK_D, () -> rightPressed = false);
        keyReleasedActions.put(KeyEvent.VK_ENTER, () -> enterPressed = false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

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

    public void reset() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        enterPressed = false;
    }
}
