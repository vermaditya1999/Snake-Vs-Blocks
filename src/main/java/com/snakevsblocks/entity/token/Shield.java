package com.snakevsblocks.entity.token;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Shield extends Token {

    public Shield(double x, double y) {

        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {

        // Display Shield
        // To be changed later
        gc.setFill(Color.CORNSILK);
        gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);

        // Display text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 15));
        gc.fillText("Shield", pos.x, pos.y + 2.5 * Token.RADIUS);

    }
}
