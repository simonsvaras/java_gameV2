package mvc.game.model.tile;

import mvc.game.controller.C_GamePanel;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    C_GamePanel gamePanel;
    public mvc.game.model.tile.Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(C_GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new mvc.game.model.tile.Tile[10];

        mapTileNum = new int[C_GamePanel.MAX_WORLD_COL][C_GamePanel.MAX_WORLD_ROW];

        getTileImage();
        loadMap("/maps/world01.txt");
    }


    public void getTileImage(){
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
    }

    // SET UP THE TILE
    // Create new Tile, load image, scale image, set collision, add to tile field
    /**
     * @param index
     * @param imageName
     * @param collision
     */
    private void setup(int index, String imageName, boolean collision){
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/"+ imageName +".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, C_GamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private  void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < C_GamePanel.MAX_WORLD_COL && row < C_GamePanel.MAX_WORLD_ROW){
                String line = br.readLine();

                while (col < C_GamePanel.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == C_GamePanel.MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e) {

        }
    }


    public Tile[] getTiles() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public int getTileAt(int x, int y) {
        return mapTileNum[x][y];
    }

    public Tile getTile(int tileNum) {
        return tile[tileNum];
    }

    public boolean isTileCollidable(int x, int y) {
        int tileI = getTileAt(x,y);
        return getTile(tileI).hasCollision();
    }


    /*
    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < C_GamePanel.MAX_WORLD_COL && worldRow < C_GamePanel.MAX_WORLD_ROW) {

            int tileNum = mapTileNum[worldCol][worldRow];

            // worldX and worldY are position on the map
            int worldX = worldCol * C_GamePanel.TILE_SIZE;
            int worldY = worldRow * C_GamePanel.TILE_SIZE;
            // screenX and screenY is where we draw it against players position
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; // offset the difference when player is in the corner
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + C_GamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - C_GamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + C_GamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - C_GamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }


            worldCol++;


            if(worldCol == C_GamePanel.MAX_WORLD_COL){
                worldCol = 0;
                worldRow++;

            }
        }

     */

    }

