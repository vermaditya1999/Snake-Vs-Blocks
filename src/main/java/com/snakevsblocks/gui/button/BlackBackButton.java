package com.snakevsblocks.gui.button;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class creates a black Back Button.
 */
public class BlackBackButton extends BackButton {

    /**
     * Create a black back button.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public BlackBackButton(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.drawImage(Image.getBBack(), pos.x - size / 2, pos.y - size / 2, size, size);
    }
}
