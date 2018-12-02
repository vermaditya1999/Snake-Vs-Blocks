package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Coin is a Token which is the in-game currency of the game.
 * It can be used to buy snake skins from the game store.
 *
 * @see Token
 */
public class Coin extends Token {

    /**
     * Creates a new Coin.
     *
     * @param x x coordinate of the Coin centre.
     * @param y y coordinate of the Coin centre.
     */
    public Coin(int x, int y) {
        super(x, y);
    }

    /**
     * Updates the position of coin with respect to
     * the speed of the game.
     */
    public void update(double speed) {
        pos.y += speed;
    }

    /**
     * Makes the coins move towards snake head.
     */
    public void attract(Vector pos) {
        this.pos.lerp(pos, 0.25);
    }

    @Override
    public void show(GraphicsContext gc) {
        if (Image.getCOIN() == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(Image.getCOIN(), pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
