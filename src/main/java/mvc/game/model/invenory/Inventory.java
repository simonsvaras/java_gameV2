package mvc.game.model.invenory;

import mvc.game.model.entity.M_Entity;


import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<M_Entity> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(M_Entity item) {
        this.items.add(item);
        // Zde můžete přidat další logiku, např. aktualizace UI inventáře
    }

    public boolean removeItem(M_Entity item) {
        return this.items.remove(item);
        // Můžete přidat logiku pro aktualizaci UI po odebrání položky
    }

    public List<M_Entity> getItems() {
        return items;
    }

    public void clear() {
        this.items.clear();
        // Další logika po vyčištění inventáře, např. aktualizace UI
    }
}
