package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Destroyer extends Token {

    public Destroyer(double x, double y) {

        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {

        // Display Destroyer
        // To be changed later
        gc.setFill(Color.HOTPINK);
        gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);

        // Display text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 15));
        gc.fillText("Destroyer", pos.x, pos.y + 2.5 * Token.RADIUS);

    }
}
