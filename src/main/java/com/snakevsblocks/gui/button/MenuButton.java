package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.window.Menu;
import javafx.scene.canvas.GraphicsContext;

public class MenuButton extends Button {

    private static final double height = App.TILE_SIZE / 2;
    private static final double width = App.TILE_SIZE * 3;

    private String text;
    private boolean hovered;

    public MenuButton(String text, double posY) {
        super(App.SCREEN_WIDTH / 2, posY);
        this.text = text;
    }

    @Override
    public void show(GraphicsContext gc) {

        // Show button
        gc.setLineWidth(2.0);
        gc.setStroke(Menu.FG_COLOR);
        gc.strokeRect(App.SCREEN_WIDTH / 2 - width / 2, pos.y - height / 2, width, height);
        if (hovered) {
            gc.setFill(Menu.FG_COLOR);
            gc.fillRect(App.SCREEN_WIDTH / 2 - width / 2, pos.y - height / 2, width, height);
        }

        // Set font
        gc.setFont(Font.CONSOLAS_SMALL);

        // Show button text
        if (hovered) {
            gc.setFill(Menu.BG_COLOR);
        } else {
            gc.setFill(Menu.FG_COLOR);
        }
        gc.fillText(text, App.SCREEN_WIDTH / 2, pos.y);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return hovered = Math.abs(mouseX - App.SCREEN_WIDTH / 2) <= width / 2 && Math.abs(mouseY - pos.y) <= height / 2;
    }
}
