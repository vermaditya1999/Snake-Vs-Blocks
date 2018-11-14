package com.snakevsblocks.entity;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Block implements Serializable {

    public static final Color COLOR = Color.rgb(241, 233, 218);

    private int value;

    private Vector pos;

    public Block(int x, int y) {

        value = 1 + Random.nextInt(10);

        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);
    }

    public void show(GraphicsContext gc) {

        double xOffset = 2;
        double yOffset = 2;

        // Display block
        gc.setFill(Block.COLOR);
        gc.fillRoundRect(pos.x - App.TILE_SIZE / 2 + xOffset, pos.y - App.TILE_SIZE / 2 + yOffset, App.TILE_SIZE - 2 * xOffset, App.TILE_SIZE - 2 * yOffset, 10, 10);

        // Show value
        gc.setFill(Color.BLACK);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText(Integer.toString(value), pos.x, pos.y);
    }

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isOnScreen() {
        return pos.y >= 0;
    }

    public Vector getPos() {
        return pos.copy();
    }

    public int getValue() {
        return value;
    }

    public boolean isOver() {
        return pos.y - App.TILE_SIZE / 2 >= App.SCREEN_HEIGHT;
    }
}
