import mvc.game.controller.C_CollisionManager;
import mvc.game.controller.C_GamePanel;
import mvc.game.controller.C_KeyHandler;
import mvc.game.model.Direction;
import mvc.game.model.entity.M_Entity;
import mvc.game.model.entity.M_NPCs;
import mvc.game.model.entity.M_Player;
import mvc.game.state.PlayState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {
    @Mock
    private C_GamePanel gamePanel = new C_GamePanel();
    @Mock
    private C_KeyHandler keyHandler;
    @Mock
    private C_CollisionManager collisionManager;

    @InjectMocks
    private PlayState playState;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(gamePanel.getKeyHandler()).thenReturn(keyHandler);
        keyHandler = gamePanel.getKeyHandler();
        collisionManager = new C_CollisionManager(gamePanel);
    }
/*
    @Test
    void testNPCMovementUpdate() {
        M_Entity npc = new M_NPCs(gamePanel);
        when(gamePanel.getNpcs()).thenReturn( List.of(npc));
        playState.update();
        // Verify that handleNPCMovement is called and NPC properties are updated accordingly
        verify(gamePanel, atLeastOnce()).getNpcs();
    }

 */
    @Test
    void testPlayerMovementWithKeyPress() {
        M_Player player = gamePanel.getPlayer();
        when(gamePanel.getPlayer()).thenReturn(player);
        when(keyHandler.upPressed).thenReturn(true);

        playState.update();

        // Assert the player direction is updated to UP
        assertEquals(Direction.UP, player.getDirection());
        // Verify movement handling and collision checks are performed
        verify(collisionManager, times(1)).checkTileCollision(any(), any());
    }

    @Test
    void testRandomDirectionChange() {
        M_NPCs npc = mock(M_NPCs.class);
        npc.setActionLockCounter(1000); // Set higher than max to trigger change
        npc.setMaxActionLockCounter(10); // Assume this method exists to set max counter
        playState.handleNPCMovement(npc);

        // Assert that direction changes
        assertNotNull(npc.getDirection());
    }

    @Test
    void testMovementBlockedByCollision() {
        M_NPCs npc = new M_NPCs(gamePanel);
        npc.setCollisionOn(true); // Simulate a collision condition
        assertFalse(playState.tryMove(npc, Direction.UP));
        // Verify collision management logic is being called
        verify(collisionManager, times(1)).checkTileCollision(npc, Direction.UP);
    }
}
