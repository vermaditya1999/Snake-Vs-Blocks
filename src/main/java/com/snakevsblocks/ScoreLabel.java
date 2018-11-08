package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ScoreLabel extends Label {
    public ScoreLabel() {
        super(App.TILE_SIZE / 2, App.TILE_SIZE / 2);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(value), pos.x, pos.y);
    }
}
