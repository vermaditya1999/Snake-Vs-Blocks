package com.snakevsblocks.entity.token;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Coin extends Token {

    private Image image;

    public Coin(int x, int y) {
        super(x, y);

        try {
            image = new Image("images/coin.png");
        } catch (Exception ex) {
            System.err.println("Invalid path!");
        }
    }

    public void update(double speed) {
        pos.y += speed;
    }

    @Override
    public void show(GraphicsContext gc) {
        if (image == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(image, pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
