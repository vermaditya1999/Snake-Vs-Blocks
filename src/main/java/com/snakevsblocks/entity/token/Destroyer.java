package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Destroyer is a Token which, when consumed, destroys
 * all the blocks appearing on the screen
 * <p>
 * The effect of Destroyer stays for a limited time and can be seen at the
 * top of the game-play screen.
 *
 * @see Token
 */
public class Destroyer extends Token {

    /**
     * Creates new Destroyer.
     * @param x x coordinate of the Destroyer centre
     * @param y y coordinate of the Destroyer centre
     */
    public Destroyer(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getDESTROYER() == null) {
            gc.setFill(Color.HOTPINK);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getDESTROYER(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
