package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Vector;
import com.snakevsblocks.window.LeaderBoard;
import javafx.scene.canvas.GraphicsContext;

public class ScorePane {

    public static final double RADIUS = App.TILE_SIZE / 4;
    private static final double HEIGHT = ScorePane.RADIUS * 2;
    private static final double WIDTH = App.TILE_SIZE * 3.5;

    private Vector pos;
    private String date;
    private int score;
    private boolean hovered;

    public ScorePane(String date, int score) {
        this.date = date;
        this.score = score;
        pos = new Vector(App.SCREEN_WIDTH / 2, 0);
        hovered = false;
    }

    public void show(GraphicsContext gc, int rank) {

        // Set font
        gc.setFont(Font.CONSOLAS_SMALL);

        // Set y coordinate of pos (Subject to rank)
        pos.y = App.TILE_SIZE * 1.25 + (App.TILE_SIZE * rank) * 0.60;

        // Show hover bar
        if (hovered) {
            gc.setFill(LeaderBoard.FG_COLOR);
            gc.fillRect(pos.x - ScorePane.WIDTH / 2 + ScorePane.RADIUS, pos.y - ScorePane.HEIGHT / 2, ScorePane.WIDTH, ScorePane.HEIGHT);
        }

        // Show rank label
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.fillOval(App.TILE_SIZE - ScorePane.RADIUS, pos.y - ScorePane.RADIUS, ScorePane.RADIUS * 2, ScorePane.RADIUS * 2);

        // Show rank
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillText(Integer.toString(rank), App.TILE_SIZE, pos.y + 1);  // +1 to adjust the baseline with Consolas font

        // Set fill color for text
        if (hovered) {
            gc.setFill(LeaderBoard.BG_COLOR);
        } else {
            gc.setFill(LeaderBoard.FG_COLOR);
        }

        // Show date and score
        gc.fillText(date, pos.x, pos.y);
        gc.fillText(Integer.toString(score), pos.x + App.TILE_SIZE * 1.5, pos.y);
    }

    public int getScore() {
        return score;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= ScorePane.WIDTH / 2 && Math.abs(mouseY - pos.y) <= ScorePane.HEIGHT / 2;
    }
}