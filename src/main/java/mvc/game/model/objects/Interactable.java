package mvc.game.model.objects;

import mvc.game.model.entity.Player;

public interface Interactable {
    void interact(Player player);
    void pickUp(Player player);
}
