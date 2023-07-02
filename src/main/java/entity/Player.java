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

        screenX = Constants.SCREEN_WIDTH/2-(Constants.TILE_SIZE/2);  //position the character in the center of the screen.
        screenY = Constants.SCREEN_HEIGHT/2-(Constants.TILE_SIZE/2);

        solidArea = new Rectangle(8,8,32,32);

        setDefaultValues();
        getPlayerImage();
        direction= "down";
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
            }
            else if (keyH.downPressed) {
                direction= "down";
            }
            else if (keyH.leftPressed) {
                direction= "left";
            }
            else if (keyH.rightPressed) {
                direction= "right";
            }
            // CHECK TILE COLLISION
            coalitionOn = false;
            gp.CD.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE

            if( coalitionOn == false){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
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
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
                break;
            default:
                // Handle the case when direction is not recognized
                // For example, you can set a default image or throw an exception
                break;
        }

        if (image != null) {
            g2.drawImage(image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        }
    }

}
