package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class EntryPane {

    public static final double RADIUS = App.TILE_SIZE / 5;

    private Vector pos;
    private double height;
    private double width;
    private String date;
    private int score;
    private boolean hovered;

    {
        pos = new Vector(App.SCREEN_WIDTH / 2, 0);
        height = EntryPane.RADIUS * 2;
        width = App.TILE_SIZE * 3.5;
        hovered = false;
    }

    public EntryPane(String date, int score) {
        this.date = date;
        this.score = score;
    }

    public void show(GraphicsContext gc, int rank) {

        // Set font
        gc.setFont(new Font("Consolas", 20));

        // Set y coordinate of pos (Subject to rank)
        pos.y = App.TILE_SIZE * 1.25 + (App.TILE_SIZE * rank) * 0.60;

        // Show hover bar
        if (hovered) {
            gc.setFill(LeaderBoard.FG_COLOR);
            gc.fillRect(pos.x - width / 2 + EntryPane.RADIUS, pos.y - height / 2, width, height);
        }

        // Show rank label
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.fillOval(App.TILE_SIZE - EntryPane.RADIUS, pos.y - EntryPane.RADIUS, EntryPane.RADIUS * 2, EntryPane.RADIUS * 2);

        // Show rank
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillText(Integer.toString(rank), App.TILE_SIZE, App.TILE_SIZE * 1.25 + (App.TILE_SIZE * rank) * 0.60 + 1);

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
        return Math.abs(mouseX - pos.x) <= width / 2 && Math.abs(mouseY - pos.y) <= height / 2;
    }
}