package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is used to create the Restart Button.
 */
public class RestartButton extends Button {

    /**
     * Size of the button.
     */
    private double size;

    /**
     * Creates an new Restart Button.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public RestartButton(double x, double y) {
        super(x, y);
        size = App.TILE_SIZE;
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.drawImage(Image.getRESTART(), pos.x - size / 2, pos.y - size / 2, size, size);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= size / 2 && Math.abs(mouseY - pos.y) <= size / 2;
    }
}
