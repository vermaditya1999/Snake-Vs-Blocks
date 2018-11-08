package com.snakevsblocks.entity.token;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.Collideable;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public abstract class Token implements Collideable {

    public final static double RADIUS = 10;

    public Vector pos;

    public boolean consumed;

    public Token(double x, double y) {

        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);
        consumed = false;
    }

    public abstract void show(GraphicsContext gc);

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isDead() {
        return pos.y - Token.RADIUS >= App.SCREEN_HEIGHT;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    @Override
    public void collide(Vector snakeHeadVector) {
        consumed = Vector.dist(snakeHeadVector, pos) <= (SnakeBall.RADIUS + Token.RADIUS);
    }
}
