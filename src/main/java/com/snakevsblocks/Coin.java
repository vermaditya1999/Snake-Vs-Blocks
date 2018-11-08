package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coin extends Token {

    public Coin(int x, int y) {
        super(x, y);
    }

    public void update(double speed) {
        pos.y += speed;
    }

    @Override
    public void show(GraphicsContext gc) {

        // Display Coin
        // To be changed later
        gc.setFill(Color.YELLOW);
        gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
    }
}
