package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import javafx.scene.canvas.GraphicsContext;

/**
 * This abstract class is used to create Back Buttons.
 */
abstract public class BackButton extends Button {

    /**
     * Size of the button, both height and width.
     */
    protected double size;

    /**
     * Creates a new Back Button.
     *
     * @param x x coordinate of the back button.
     * @param y y coordinate of the back button.
     */
    public BackButton(double x, double y) {
        super(x, y);
        size = App.TILE_SIZE / 2;
    }

    @Override
    abstract public void show(GraphicsContext gc);

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= size / 2 && Math.abs(mouseY - pos.y) <= size / 2;
    }
}
