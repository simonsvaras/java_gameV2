package mvc.game.controller;

import mvc.game.model.M_Entity;

import java.awt.*;
import java.util.ArrayList;

public class C_RenderManager {
    public void render(Graphics2D g2, ArrayList<M_Entity> entities) {
        for (M_Entity entity : entities) {
            entity.draw(g2);
        }
    }
}
