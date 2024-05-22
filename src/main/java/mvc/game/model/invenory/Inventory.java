package mvc.game.model.invenory;

import mvc.game.model.objects.GameObject;
import mvc.game.model.objects.Interactable;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Interactable> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Interactable item) {
        this.items.add(item);
        // Zde můžete přidat další logiku, např. aktualizace UI inventáře
    }

    public boolean removeItem(Interactable item) {
        return this.items.remove(item);
        // Můžete přidat logiku pro aktualizaci UI po odebrání položky
    }

    public List<Interactable> getItems() {
        return items;
    }

    public void clear() {
        this.items.clear();
        // Další logika po vyčištění inventáře, např. aktualizace UI
    }
}
