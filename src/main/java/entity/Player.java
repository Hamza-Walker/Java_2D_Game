package entity;

import com.walker.GamePanel;
import com.walker.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }

    public void setDefaultValues () {
        x = 100;
        y = 100;
        speed = 4;
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
        e.printStackTrace();
        }

    }
    public void update (){
        if (keyH.upPressed) {
            direction= "up";
            y -= speed;
        } else if (keyH.downPressed) {
            direction= "down";

            y += speed;
        } else if (keyH.leftPressed) {
            direction= "left";

            x -= speed;
        } else if (keyH.rightPressed) {
            direction= "right";

            x += speed;
        }
    }

    public void draw (Graphics2D g2){
       // g2.setColor(Color.white);
      //  g2.fillRect(x, y, gp.tileSize, gp.tileSize); // Draw the player character
        BufferedImage image=null;
        switch (direction) {
            case "up" -> {
                ;
                image = up1;
            }
            case "down" -> {
                ;
                image = down1;
            }
            case "left" -> {
                ;
                image = left1;
            }
            case "right" -> {
                ;
                image = right1;
            }
        }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
