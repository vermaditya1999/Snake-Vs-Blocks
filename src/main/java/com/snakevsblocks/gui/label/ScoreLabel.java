package com.snakevsblocks.gui.label;

import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ScoreLabel extends Label {
    public ScoreLabel(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x, pos.y + 1);
    }
}
