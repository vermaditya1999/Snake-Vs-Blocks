package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Magnet is a Token which, when consumed, attracts Coins
 * towards the snake, when they are in vicinity to it.
 * <p>
 * The effect of Magnet stays for a limited time and can be seen at the
 * top of the game-play screen.
 *
 * @see Token
 */
public class Magnet extends Token {

    /**
     * Creates new Magnet.
     * @param x x coordinate of the Magnet centre
     * @param y y coordinate of the Magnet centre
     */
    public Magnet(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getMAGNET() == null) {
            gc.setFill(Color.DARKSLATEBLUE);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getMAGNET(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
