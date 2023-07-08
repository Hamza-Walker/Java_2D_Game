package object;

import com.walker.Constants;
import com.walker.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (    worldX + Constants.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - Constants.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + Constants.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - Constants.TILE_SIZE < gp.player.worldY + gp.player.screenY
        ){
            g2.drawImage(image,screenX,screenY, Constants.TILE_SIZE,Constants.TILE_SIZE,null);
        }
    }
}
