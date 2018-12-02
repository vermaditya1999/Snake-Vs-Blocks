package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Star is a rare special Token which, when consumed, triples the speed of the
 * game-play, and adds Shield effect to the snake.
 * <p>
 * The special effect stays for a limited time and can be seen at the
 * top of the game-play screen.
 *
 * @see Token
 */
public class Star extends Token {

    /**
     * Angle from the y-axis.
     * Used to create the shape of the star, by incrementing
     * by a fixed amount iteratively.
     */
    private int angle;

    /**
     * Creates a new Star.
     * @param x x coordinate of the Star centre.
     * @param y y coordinate of the Star centre.
     */
    public Star(double x, double y) {
        super(x, y);
        angle = 0;
    }

    @Override
    public void show(GraphicsContext gc) {
        angle += 2;
        if (angle > 360) {
            angle = 0;
        }

        gc.translate(pos.x, pos.y);
        gc.rotate(angle);
        if (Image.getSTAR() == null) {
            gc.setFill(Color.HOTPINK);
            gc.fillOval(0 - radius, 0 - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getSTAR(), 0 - radius, 0 - radius, 2 * radius, 2 * radius);
        }
        gc.rotate(-angle);
        gc.translate(-pos.x, -pos.y);
    }
}
