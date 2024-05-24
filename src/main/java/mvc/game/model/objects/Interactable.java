package mvc.game.model.objects;

import mvc.game.model.entity.M_Player;

public interface Interactable {
    void interact(M_Player player);
    void pickUp(M_Player player);
}
