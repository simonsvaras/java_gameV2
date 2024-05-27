package mvc.game.model.invenory;

import mvc.game.model.entity.Entity;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Entity> items;

    public Inventory() {
        this.items = new LinkedList<>();
    }

    public void addItem(Entity item) {
        this.items.add(item);

    }

    public boolean removeItem(Entity item) {
        return this.items.remove(item);

    }

    public List<Entity> getItems() {
        return items;
    }

    public void clear() {
        this.items.clear();

    }
}
