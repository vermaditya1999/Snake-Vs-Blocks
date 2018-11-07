package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Coin extends Token {

    public static boolean attractable = false;

    public Coin(int x, int y) {
        super(x, y);
    }

    public void update(double speed, Vector snakeHeadVec) {
        pos.y += speed;

        if (attractable && pos.y >= snakeHeadVec.y) {
            Vector dir = Vector.sub(snakeHeadVec, pos);
            dir.setMag(35);
            pos.add(dir);
        }
    }

    @Override
    public void show(GraphicsContext gc) {

        // Display Coin
        // To be changed later
        gc.setFill(Color.YELLOW);
        gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);

        // Display text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 15));
        gc.fillText("Coin", pos.x, pos.y + 2.5 * Token.RADIUS);

    }
}
