package com.snakevsblocks.gui.label;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * This class is used to create a Score label.
 */
public class ScoreLabel extends Label {

    /**
     * Create a new Score label.
     */
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

        gc.setFill(Color.rgb(SnakeBall.COLOR[0], SnakeBall.COLOR[1], SnakeBall.COLOR[2]));
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);
    }
}
