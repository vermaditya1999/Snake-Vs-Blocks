package com.snakevsblocks.gui.label;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ScoreLabel extends Label {

    public ScoreLabel() {
        super(App.TILE_SIZE / 2, App.TILE_SIZE / 2);
    }

    @Override
    public void show(GraphicsContext gc) {

        gc.setTextAlign(TextAlignment.LEFT);

        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x + SnakeBall.RADIUS + App.TILE_SIZE / 4, pos.y + 1);

        gc.setTextAlign(TextAlignment.CENTER);

        gc.setFill(SnakeBall.COLOR);
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);
    }
}
