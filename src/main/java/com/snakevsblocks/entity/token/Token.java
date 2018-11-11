package com.snakevsblocks.entity.token;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public abstract class Token implements Consumable {

    public static final double RADIUS = 15;

    private static final double INNER_RADIUS = 12;

    protected Vector pos;
    protected double radius;

    private boolean shrinkDir;  // True if the token is shrinking
    private boolean consumed;

    public Token(double x, double y) {
        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);
        radius = Token.RADIUS;
        shrinkDir = true;
        consumed = false;
    }

    public void show(GraphicsContext gc) {
        if (radius <= Token.INNER_RADIUS) {
            shrinkDir = false;
            radius = Token.INNER_RADIUS;
        } else if (radius >= Token.RADIUS) {
            shrinkDir = true;
            radius = Token.RADIUS;
        }

        if (shrinkDir) {
            radius -= 0.1;
        } else {
            radius += 0.1;
        }
    }

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isDead() {
        return pos.y - Token.RADIUS >= App.SCREEN_HEIGHT;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
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
