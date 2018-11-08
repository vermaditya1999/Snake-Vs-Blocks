package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CoinLabel extends Label {
    public CoinLabel(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x, pos.y);

        gc.setFill(Color.YELLOW);
        gc.fillOval(pos.x - App.TILE_SIZE / 2 - Token.RADIUS,
                pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
    }
}
