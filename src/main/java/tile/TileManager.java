package tile;

import com.walker.Constants;
import com.walker.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[Constants.MAX_SCREEN_COL][Constants.MAX_SCREEN_ROW];

        getTileImage();
        loadMap("/maps/Map01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap( String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < Constants.MAX_SCREEN_COL && row < Constants.MAX_SCREEN_ROW) {
                String line = br.readLine();
                while (col < Constants.MAX_SCREEN_COL) {
                    String[] numbers = line.split(",");
                    int num = Integer.parseInt(numbers[col].trim());  // Trim the number before parsing
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == Constants.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0 ;
        int y = 0 ;

        while (col < Constants.MAX_SCREEN_COL && row < Constants.MAX_SCREEN_ROW){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y, Constants.TILE_SIZE,Constants.TILE_SIZE,null);
            col++;
            x += Constants.TILE_SIZE;

            if ( col == Constants.MAX_SCREEN_COL){
                col = 0;
                x = 0;
                row++;
                y +=  Constants.TILE_SIZE;
            }
        }
    }
}
