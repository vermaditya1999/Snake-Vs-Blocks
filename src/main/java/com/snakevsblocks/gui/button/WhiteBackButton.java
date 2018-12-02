package com.snakevsblocks.gui.button;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is used to create a white Back Button.
 */
public class WhiteBackButton extends BackButton {

    /**
     * Create a white Back Button.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public WhiteBackButton(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.drawImage(Image.getWBack(), pos.x - size / 2, pos.y - size / 2, size, size);
    }
}
