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

    private double size;

    public Block(int x, int y, int snakeLength) {

        value = generateValue(snakeLength);

        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);

        size = App.TILE_SIZE;
    }

    public void show(GraphicsContext gc) {

        double xOffset = 2;
        double yOffset = 2;

        if (size > App.TILE_SIZE) {
            size -= 0.5;
        }

        // Display block
        gc.setFill(Block.COLOR);
        gc.fillRoundRect(pos.x - size / 2 + xOffset, pos.y - size / 2 + yOffset, size - 2 * xOffset, size - 2 * yOffset, 10, 10);

        if (size > App.TILE_SIZE) {
            gc.setFill(new Color(1, 1, 1, 0.25));
            gc.fillRoundRect(pos.x - size / 2 + xOffset, pos.y - size / 2 + yOffset, size - 2 * xOffset, size - 2 * yOffset, 10, 10);
        }

        // Show value
        gc.setFill(Color.BLACK);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText(Integer.toString(value), pos.x, pos.y);
    }

    private int generateValue(int snakeLength) {

        int offSet = 10;
        value = snakeLength / 2 - offSet + Random.nextInt(2 * offSet);

        while (value < 1) {
            value = Random.nextInt(snakeLength / 2 + 2);
        }

        return value;
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

    public boolean expand() {
        size = App.TILE_SIZE + 5;
        if (value == 1) {
            return true;
        } else {
            value--;
            return false;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }
}
