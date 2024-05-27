import mvc.game.controller.CollisionManager;
import mvc.game.controller.GamePanel;
import mvc.game.controller.KeyHandler;
import mvc.game.model.Direction;
import mvc.game.model.entity.NPCs;
import mvc.game.model.entity.Player;
import mvc.game.state.PlayState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {
    @Mock
    private GamePanel gamePanel = new GamePanel();
    @Mock
    private KeyHandler keyHandler;
    @Mock
    private CollisionManager collisionManager;

    @InjectMocks
    private PlayState playState;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(gamePanel.getKeyHandler()).thenReturn(keyHandler);
        keyHandler = gamePanel.getKeyHandler();
        collisionManager = new CollisionManager(gamePanel);
    }
/*
    @Test
    void testNPCMovementUpdate() {
        Entity npc = new NPCs(gamePanel);
        when(gamePanel.getNpcs()).thenReturn( List.of(npc));
        playState.update();
        // Verify that handleNPCMovement is called and NPC properties are updated accordingly
        verify(gamePanel, atLeastOnce()).getNpcs();
    }

 */
    @Test
    void testPlayerMovementWithKeyPress() {
        Player player = gamePanel.getPlayer();
        when(gamePanel.getPlayer()).thenReturn(player);
        when(keyHandler.isUpPressed()).thenReturn(true);

        playState.update();

        // Assert the player direction is updated to UP
        assertEquals(Direction.UP, player.getDirection());
        // Verify movement handling and collision checks are performed
        verify(collisionManager, times(1)).checkTileCollision(any(), any());
    }

    @Test
    void testRandomDirectionChange() {
        NPCs npc = mock(NPCs.class);
        npc.setActionLockCounter(1000); // Set higher than max to trigger change
        npc.setMaxActionLockCounter(10); // Assume this method exists to set max counter
        playState.handleNPCMovement(npc);

        // Assert that direction changes
        assertNotNull(npc.getDirection());
    }

    @Test
    void testMovementBlockedByCollision() {
        NPCs npc = new NPCs(gamePanel);
        npc.setCollisionOn(true); // Simulate a collision condition
        assertFalse(playState.tryMove(npc, Direction.UP));
        // Verify collision management logic is being called
        verify(collisionManager, times(1)).checkTileCollision(npc, Direction.UP);
    }
}
