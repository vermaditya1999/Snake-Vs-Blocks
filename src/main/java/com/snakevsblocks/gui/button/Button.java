package com.snakevsblocks.gui.button;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * This abstract class is used to create buttons.
 */
public abstract class Button implements Serializable {

    /**
     * Position vector of the button.
     */
    protected Vector pos;

    /**
     * Creates a new Button.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Button(double x, double y) {
        pos = new Vector(x, y);
    }

    /**
     * Show the button.
     * @param gc the graphics context on which to show the button.
     */
    abstract public void show(GraphicsContext gc);

    /**
     * @param mouseX x coordinate of the mouse.
     * @param mouseY y coordinate of the mouse.
     * @return true is button is hovered.
     */
    abstract public boolean isHovered(double mouseX, double mouseY);
}
