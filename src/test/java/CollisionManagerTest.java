package mvc.game.controller;

import mvc.game.model.Direction;
import mvc.game.model.entity.LiveObjects;
import mvc.game.model.entity.NPCs;
import mvc.game.model.tile.TileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollisionManagerTest {
    private CollisionManager collisionManager;
    private GamePanel mockGamePanel;
    private TileManager mockTileManager;
    private LiveObjects mockEntity;
    private NPCs mockNPC;

    @BeforeEach
    public void setUp() {
        mockGamePanel = mock(GamePanel.class);
        mockTileManager = mock(TileManager.class);
        collisionManager = new CollisionManager(mockGamePanel);
        mockEntity = mock(LiveObjects.class);
        mockNPC = mock(NPCs.class);

        when(mockGamePanel.getTileManager()).thenReturn(mockTileManager);
    }

    @Test
    public void testCheckTileCollision_noCollision() {
        // Mock setup
        when(mockEntity.getWorldX()).thenReturn(100);
        when(mockEntity.getWorldY()).thenReturn(100);
        when(mockEntity.getSpeed()).thenReturn(10);
        when(mockEntity.getSolidArea()).thenReturn(new Rectangle(0, 0, 30, 30));
        when(mockTileManager.isTileCollidable(anyInt(), anyInt())).thenReturn(false);

        // Execute
        boolean collision = collisionManager.checkTileCollision(mockEntity, Direction.UP);

        // Verify
        assertFalse(collision);
        verify(mockEntity).setCollisionOn(false);
    }

    @Test
    public void testCheckEntity_noCollision() {
        // Mock setup
        ArrayList<NPCs> entities = new ArrayList<>();
        entities.add(mockNPC);

        when(mockEntity.getWorldX()).thenReturn(100);
        when(mockEntity.getWorldY()).thenReturn(100);
        when(mockEntity.getSpeed()).thenReturn(10);
        when(mockEntity.getSolidArea()).thenReturn(new Rectangle(0, 0, 30, 30));
        when(mockEntity.getDirection()).thenReturn(Direction.UP);

        when(mockNPC.getWorldX()).thenReturn(200);
        when(mockNPC.getWorldY()).thenReturn(200);
        when(mockNPC.getSolidArea()).thenReturn(new Rectangle(0, 0, 30, 30));

        // Execute
        boolean collision = collisionManager.checkEntity(mockEntity, entities);

        // Verify
        assertFalse(collision);
        verify(mockEntity, never()).setCollisionOn(true);
    }

    @Test
    public void testCheckEntity_withCollision() {
        // Mock setup
        ArrayList<NPCs> entities = new ArrayList<>();
        entities.add(mockNPC);

        when(mockEntity.getWorldX()).thenReturn(100);
        when(mockEntity.getWorldY()).thenReturn(100);
        when(mockEntity.getSpeed()).thenReturn(10);
        when(mockEntity.getSolidArea()).thenReturn(new Rectangle(0, 0, 30, 30));
        when(mockEntity.getDirection()).thenReturn(Direction.UP);

        when(mockNPC.getWorldX()).thenReturn(105);
        when(mockNPC.getWorldY()).thenReturn(105);
        when(mockNPC.getSolidArea()).thenReturn(new Rectangle(0, 0, 30, 30));

        // Execute
        boolean collision = collisionManager.checkEntity(mockEntity, entities);

        // Verify
        assertTrue(collision);
        verify(mockEntity).setCollisionOn(true);
    }
}
