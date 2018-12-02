package com.snakevsblocks.entity;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * SnakeBall is an entity, which makes up the body of the snake.
 */
public class SnakeBall implements Serializable {

    /**
     * Radius of the snakeBall.
     */
    public static final double RADIUS = 10;

    /**
     * Color of the snakeBall.
     */
    public static int[] COLOR;

    /**
     * Position vector of the snakeBall.
     */
    private Vector pos;

    /**
     * Constructs a new Snake Ball.
     *
     * @param x x coordinate of the centre of the snake ball
     * @param y y coordinate of the centre of the snake ball
     */
    public SnakeBall(double x, double y) {
        pos = new Vector(x, y);
    }

    /**
     * Display the snake ball on the screen.
     * @param gc Graphic context on which to show the token.
     * @param color Color of the snakeBall
     */
    public void show(GraphicsContext gc, int[] color) {

        // Display ball
        gc.setFill(Color.rgb(color[0], color[1], color[2]));
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(double x, double y) {
        pos.x = x;
        pos.y = y;
    }
}
