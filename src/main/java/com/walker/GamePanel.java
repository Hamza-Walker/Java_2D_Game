package com.walker;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // Standard character size: 16x16 tiles
    final int scale = 3; // Compensate for the resolution difference
    public final int tileSize = originalTileSize * scale; // Size of tiles after scaling: 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // Ratio is 4:3
    final int screenWidth = tileSize * maxScreenCol; // Screen width: 48 * 16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // Screen height: 48 * 12 = 576 pixels

    // FPS
    final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    // Set player's default position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Enabling double buffering can improve rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // GamePanel can be focused to receive key input
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Passing the GamePanel instance to the thread
        gameThread.start();
    }

    // Uses the sleep method to slow down the rendering
//    @Override
//    public void run() {
//        double drawInterval = 1000000000.0 / FPS; // 1 second / 60 FPS = 0.01666 seconds per frame
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//            update(); // 1. UPDATE: Update information such as character position, game logic, etc.
//
//            repaint(); // 2. DRAW: Draw the screen with the updated information using graphics rendering
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime(); // Finding the sleep interval for the thread
//                remainingTime = remainingTime / 1000000.0; // Converting to milliseconds (sleep method requires milliseconds)
//
//                if (remainingTime < 0) remainingTime = 0; // Small check, not significant
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public void run () {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;


            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the parent's paintComponent method (JPanel)

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose(); // Dispose of this graphics context and release any system resources that it is using
    }
}
