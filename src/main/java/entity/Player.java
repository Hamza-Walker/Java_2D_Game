package entity;

import com.walker.Constants;
import com.walker.GamePanel;
import com.walker.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = Constants.SCREEN_HEIGHT/2-(Constants.TILE_SIZE/2);  //position the character in the center of the screen.
        screenY = Constants.SCREEN_HEIGHT/2-(Constants.TILE_SIZE/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {
        worldX = Constants.TILE_SIZE * 23;
        worldY = Constants.TILE_SIZE * 21;
        speed = 4;
    }
    public void getPlayerImage(){
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    public void update (){
        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed){

            if (keyH.upPressed) {
                direction= "up";
                worldY -= speed;
            }
            else if (keyH.downPressed) {
                direction= "down";

                worldY += speed;
            }
            else if (keyH.leftPressed) {
                direction= "left";

                worldX -= speed;
            }
            else if (keyH.rightPressed) {
                direction= "right";

                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNumber == 1){
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNumber == 1) {
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
            }
        }
            g2.drawImage(image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
    }
}
