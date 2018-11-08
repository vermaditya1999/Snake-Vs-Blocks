package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
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
        gc.fillText(text, pos.x, pos.y);
    }
}