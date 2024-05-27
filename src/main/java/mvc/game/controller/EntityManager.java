package mvc.game.controller;

import mvc.game.model.entity.NPC_OldMan;
import mvc.game.model.entity.monsters.GreenSlime;

/**
 * Manages the entities in the game.
 */
public class EntityManager {
    private GamePanel gamePanel;

    /**
     * Constructs an entity manager with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public EntityManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Sets the NPCs in the game.
     */
    public void setNPC() {
        // ADD NPC OLD MAN
        addOldManNPC(GamePanel.TILE_SIZE * 18, GamePanel.TILE_SIZE * 10);
        addOldManNPC(GamePanel.TILE_SIZE * 20, GamePanel.TILE_SIZE * 12);
        addOldManNPC(GamePanel.TILE_SIZE * 22, GamePanel.TILE_SIZE * 14);

        // ADD NPC SLIME MONSTER
        addGreenSlimeNPC(GamePanel.TILE_SIZE * 24, GamePanel.TILE_SIZE * 16);
    }

    /**
     * Adds an old man NPC to the game panel at the specified coordinates.
     *
     * @param worldX The x-coordinate in the world.
     * @param worldY The y-coordinate in the world.
     */
    private void addOldManNPC(int worldX, int worldY) {
        NPC_OldMan oldMan = new NPC_OldMan(gamePanel);
        oldMan.setWorldX(worldX);
        oldMan.setWorldY(worldY);
        gamePanel.addNPC(oldMan);
    }

    /**
     * Adds a green slime NPC to the game panel at the specified coordinates.
     *
     * @param worldX The x-coordinate in the world.
     * @param worldY The y-coordinate in the world.
     */
    private void addGreenSlimeNPC(int worldX, int worldY) {
        GreenSlime greenSlime = new GreenSlime(gamePanel);
        greenSlime.setWorldX(worldX);
        greenSlime.setWorldY(worldY);
        gamePanel.addNPC(greenSlime);
    }
}
