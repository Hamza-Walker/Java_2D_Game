package com.walker;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // By passing this to the Thread constructor, you're telling the thread to execute the run() method of the GamePanel instance.
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
        double drawInterval = 1000000000.0 / Constants.FPS;
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
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose(); // Dispose of this graphics context and release any system resources that it is using
    }
}
