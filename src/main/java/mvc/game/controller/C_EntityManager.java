package mvc.game.controller;

import mvc.game.model.entity.M_NPC_OldMan;
import mvc.game.model.entity.monsters.M_GreenSlime;

/**
 * Manages the entities in the game.
 */
public class C_EntityManager {
    private C_GamePanel gamePanel;

    /**
     * Constructs an entity manager with the specified game panel.
     *
     * @param gamePanel The game panel.
     */
    public C_EntityManager(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Sets the NPCs in the game.
     */
    public void setNPC() {
        // ADD NPC OLD MAN
        addOldManNPC(C_GamePanel.TILE_SIZE * 18, C_GamePanel.TILE_SIZE * 10);
        addOldManNPC(C_GamePanel.TILE_SIZE * 20, C_GamePanel.TILE_SIZE * 12);
        addOldManNPC(C_GamePanel.TILE_SIZE * 22, C_GamePanel.TILE_SIZE * 14);

        // ADD NPC SLIME MONSTER
        addGreenSlimeNPC(C_GamePanel.TILE_SIZE * 24, C_GamePanel.TILE_SIZE * 16);
    }

    /**
     * Adds an old man NPC to the game panel at the specified coordinates.
     *
     * @param worldX The x-coordinate in the world.
     * @param worldY The y-coordinate in the world.
     */
    private void addOldManNPC(int worldX, int worldY) {
        M_NPC_OldMan oldMan = new M_NPC_OldMan(gamePanel);
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
        M_GreenSlime greenSlime = new M_GreenSlime(gamePanel);
        greenSlime.setWorldX(worldX);
        greenSlime.setWorldY(worldY);
        gamePanel.addNPC(greenSlime);
    }
}
