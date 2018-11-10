package com.snakevsblocks.gui.label;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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
        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x, pos.y + 1);

        if (image == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(image, pos.x - App.TILE_SIZE / 2 - Token.RADIUS,
                    pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
