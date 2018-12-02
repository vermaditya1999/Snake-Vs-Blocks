package com.snakevsblocks.entity;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class SnakeBall implements Serializable {

    public static final double RADIUS = 10;
    public static int[] COLOR;

    private Vector pos;

    public SnakeBall(double x, double y) {
        pos = new Vector(x, y);
    }

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
