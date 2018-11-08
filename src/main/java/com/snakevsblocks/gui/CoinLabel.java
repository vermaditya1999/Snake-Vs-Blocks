package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CoinLabel extends Label {
    public CoinLabel() {
        super(App.SCREEN_WIDTH - App.TILE_SIZE / 2, App.TILE_SIZE / 2);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(value), pos.x, pos.y);

        gc.setFill(Color.YELLOW);
        gc.fillOval(App.SCREEN_WIDTH - App.TILE_SIZE - Token.RADIUS,
                App.TILE_SIZE / 2 - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
    }
}
