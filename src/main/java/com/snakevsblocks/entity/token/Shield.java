package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Shield is a Token which, when consumed, protects the snake
 * from incoming blocks by destroying them when collided with snake.
 * <p>
 * The effect of Shield stays for a limited time and can be seen at the
 * top of the game-play screen.
 *
 * @see Token
 */
public class Shield extends Token {

    /**
     * Creates a new Shield.
     * @param x x coordinate of the Shield centre
     * @param y x coordinate of the Shield centre
     */
    public Shield(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getSHIELD() == null) {
            gc.setFill(Color.CORNSILK);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getSHIELD(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
