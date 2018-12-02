package com.snakevsblocks.gui.label;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * This abstract class is used to create Labels for displaying scores.
 */
public abstract class Label implements Serializable {

    /**
     * Position vector of the label.
     */
    protected Vector pos;

    /**
     * Text to display.
     */
    protected String text;

    /**
     * Creates a new label.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Label(double x, double y) {
        pos = new Vector(x, y);
    }

    /**
     * Update the label.
     * @param text the updated text.
     */
    public void update(String text) {
        this.text = text;
    }

    /**
     * Show the label.
     * @param gc the graphics context on which to show the label.
     */
    public abstract void show(GraphicsContext gc);
}
