package com.snakevsblocks.entity;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBall {

    public static final double RADIUS = 10;
    public static final Color COLOR = Color.rgb(175, 224, 206);

    private Vector pos;

    public SnakeBall(double x, double y) {
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc) {

        // Display ball
        gc.setFill(SnakeBall.COLOR);
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);
    }

    public Vector getPos() {
        return pos;
    }
}
