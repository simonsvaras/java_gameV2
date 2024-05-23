package mvc.game.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Sprites {
    private Map<String, BufferedImage[]> sprites = new HashMap<>();

    public void loadSprite(String action, BufferedImage... frames) {
        sprites.put(action, frames);
    }

    public BufferedImage getFrame(String action, int frameIndex) {
        BufferedImage[] frames = sprites.get(action);
        if (frames == null || frames.length <= frameIndex) return null;
        return frames[frameIndex];
    }
}
