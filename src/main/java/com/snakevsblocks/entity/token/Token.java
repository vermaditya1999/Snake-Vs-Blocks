package com.snakevsblocks.entity.token;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * Abstract Token class used to create Tokens which appear
 * in game-play.
 * <p>
 * The following are the types of Tokens:
 *
 * @see PickupBall
 * @see Coin
 * @see Destroyer
 * @see Shield
 * @see Star
 */
public abstract class Token implements Consumable, Serializable {

    /**
     * Radius of the token.
     */
    public static final double RADIUS = 15;

    /**
     * Least radius for shrink token.
     */
    private static final double INNER_RADIUS = 13;

    /**
     * Position vector of the centre of the token.
     */
    protected Vector pos;
    protected double radius;

    /**
     * Sets to True if the token is shrinking.
     */
    private boolean shrinkDir;

    /**
     * Sets to True if the token is consumed by the snake.
     */
    private boolean consumed;

    /**
     * Creates a new Token.
     * @param x x coordinate of the Token centre.
     * @param y y coordinate of the Token centre
     */
    public Token(double x, double y) {
        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);
        radius = Token.RADIUS;
        shrinkDir = true;
        consumed = false;
    }

    /**
     * Displays token on the screen.
     * @param gc Graphic context on which to show the token.
     */
    public void show(GraphicsContext gc) {
        if (radius <= Token.INNER_RADIUS) {
            shrinkDir = false;
            radius = Token.INNER_RADIUS;
        } else if (radius >= Token.RADIUS) {
            shrinkDir = true;
            radius = Token.RADIUS;
        }

        if (shrinkDir) {
            radius -= 0.05;
        } else {
            radius += 0.05;
        }
    }

    /**
     * Updates speed of the token to that of game-play.
     * @param speed speed of the game-play.
     */
    public void update(double speed) {
        pos.y += speed;
    }

    /**
     * Checks if the token has exited the game screen.
     * @return true if token has exited the screen.
     */
    public boolean isOver() {
        return pos.y - Token.RADIUS >= App.SCREEN_HEIGHT;
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public void consume(Vector snakeHeadVector) {
        consumed = Vector.dist(snakeHeadVector, pos) <= (SnakeBall.RADIUS + Token.RADIUS);
    }

    public Vector getPos() {
        return pos.copy();
    }
}
