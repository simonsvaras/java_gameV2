package mvc.game.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the sprites for different actions of an entity.
 */
public class Sprites {
    private Map<String, BufferedImage[]> sprites = new HashMap<>();

    /**
     * Loads the frames for a specific action.
     *
     * @param action The action for which the frames are loaded.
     * @param frames The frames to load for the action.
     */
    public void loadSprite(String action, BufferedImage... frames) {
        sprites.put(action, frames);
    }

    /**
     * Gets the frame for a specific action and frame index.
     *
     * @param action The action for which the frame is needed.
     * @param frameIndex The index of the frame.
     * @return The frame image, or null if the frame does not exist.
     */
    public BufferedImage getFrame(String action, int frameIndex) {
        BufferedImage[] frames = sprites.get(action);
        if (frames == null || frames.length <= frameIndex) {
            return null;
        }
        return frames[frameIndex];
    }
}
