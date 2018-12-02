package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.window.Info;
import com.snakevsblocks.window.Store;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class BuyButton extends Button {

    private static final double WIDTH = App.TILE_SIZE * 1.5;
    private static final double HEIGHT = App.TILE_SIZE / 2;

    public enum ButtonState {
        NA,
        BUY,
        SELECT,
        SELECTED
    }

    private boolean hovered;
    private ButtonState state;
    private int value;
    private int[] color;
    private String colorName;

    public BuyButton(double y, ButtonState state, int value, int[] color, String colorName) {
        super(App.SCREEN_WIDTH - App.TILE_SIZE, y);
        this.state = state;
        this.value = value;
        this.color = color;
        this.colorName = colorName;
    }

    @Override
    public void show(GraphicsContext gc) {

        // Show button
        gc.setLineWidth(2.0);
        gc.setStroke(Store.FG_COLOR);
        if (state == ButtonState.NA) {
            gc.setStroke(Color.rgb(150, 150, 150));
        }
        gc.strokeRect(pos.x - BuyButton.WIDTH / 2, pos.y - BuyButton.HEIGHT / 2, BuyButton.WIDTH, BuyButton.HEIGHT);

        if (hovered && state != ButtonState.NA || state == ButtonState.SELECTED) {
            gc.setFill(Store.FG_COLOR);
            gc.fillRect(pos.x - BuyButton.WIDTH / 2, pos.y - BuyButton.HEIGHT / 2, BuyButton.WIDTH, BuyButton.HEIGHT);
        }

        // Set font
        gc.setFont(Font.CONSOLAS_SMALL);

        // Show button text
        if (state == ButtonState.NA) {
            gc.setFill(Color.rgb(150, 150, 150));
        } else if (state == ButtonState.SELECTED) {
            gc.setFill(Color.WHITE);
        } else {
            if (hovered) {
                gc.setFill(Store.BG_COLOR);
            } else {
                gc.setFill(Store.FG_COLOR);
            }
        }
        String text = "";
        if (state == ButtonState.SELECT) {
            gc.fillText("Choose", pos.x, pos.y);
        } else if (state == ButtonState.SELECTED) {
            gc.fillText("Current", pos.x, pos.y);
        } else {
            gc.fillText("Buy", pos.x, pos.y);
        }

        // Display color name
        gc.setFill(Info.FG_COLOR);
        gc.setFont(Font.CONSOLAS_SMALL);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(colorName, pos.x - App.TILE_SIZE * 3.5, pos.y);
        gc.setTextAlign(TextAlignment.CENTER);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return hovered = Math.abs(mouseX - pos.x) <= BuyButton.WIDTH / 2 && Math.abs(mouseY - pos.y) <= BuyButton.HEIGHT / 2;
    }

    public ButtonState getState() {
        return state;
    }

    public void setState(ButtonState state) {
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public int[] getColor() {
        return color;
    }
}
