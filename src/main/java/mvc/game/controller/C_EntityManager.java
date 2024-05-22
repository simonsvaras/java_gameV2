package mvc.game.controller;

import mvc.game.model.M_Entity;

import java.util.ArrayList;

public class C_EntityManager {
    private ArrayList<M_Entity> entities = new ArrayList<>();

    public void update() {
        for (M_Entity entity : entities) {
            entity.update();
        }
    }

    public void addEntity(M_Entity entity) {
        entities.add(entity);
    }

    public ArrayList<M_Entity> getEntities() {
        return entities;
    }
}
