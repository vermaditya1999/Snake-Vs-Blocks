package com.snakevsblocks.gui.label;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class CoinLabel extends Label {

    private Image image;

    public CoinLabel(double x, double y) {
        super(x, y);

        try {
            image = new Image("images/coin.png");
        } catch (Exception ex) {
            System.err.println("Invalid path!");
        }
    }

    @Override
    public void show(GraphicsContext gc) {

        gc.setTextAlign(TextAlignment.LEFT);

        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x + App.TILE_SIZE / 4 + (Token.RADIUS - SnakeBall.RADIUS), pos.y + 1);

        gc.setTextAlign(TextAlignment.CENTER);

        if (image == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(image, pos.x - Token.RADIUS,
                    pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
