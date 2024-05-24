import mvc.game.controller.C_GamePanel;
import mvc.game.model.entity.LiveObjects;
import mvc.game.model.entity.M_Entity;
import mvc.game.model.invenory.Inventory;
import org.game.main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;

    private C_GamePanel gamePanel = new C_GamePanel();
    private M_Entity item1;
    private M_Entity item2;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        item1 = new LiveObjects(gamePanel); // Assuming M_Entity has a no-arg constructor
        item2 = new LiveObjects(gamePanel);
    }

    @Test
    void testAddItem() {
        inventory.addItem(item1);
        assertEquals(1, inventory.getItems().size(), "Inventory should have one item after adding one item.");
        assertTrue(inventory.getItems().contains(item1), "Inventory should contain the added item.");
    }

    @Test
    void testRemoveItem() {
        inventory.addItem(item1);
        assertTrue(inventory.removeItem(item1), "Item should be successfully removed from the inventory.");
        assertFalse(inventory.getItems().contains(item1), "Inventory should not contain the removed item.");
    }

    @Test
    void testRemoveItemNotPresent() {
        inventory.addItem(item1);
        assertFalse(inventory.removeItem(item2), "Removing an item not present in the inventory should return false.");
        assertEquals(1, inventory.getItems().size(), "Inventory size should remain unchanged after attempting to remove an item that is not present.");
    }

    @Test
    void testGetItems() {
        inventory.addItem(item1);
        inventory.addItem(item2);
        List<M_Entity> items = inventory.getItems();
        assertEquals(2, items.size(), "Inventory should return the correct number of items.");
        assertTrue(items.contains(item1) && items.contains(item2), "Inventory should return the correct items.");
    }

    @Test
    void testClear() {
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.clear();
        assertEquals(0, inventory.getItems().size(), "Inventory should be empty after clearing.");
    }
}
