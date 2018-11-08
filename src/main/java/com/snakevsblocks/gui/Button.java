package com.snakevsblocks.gui;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public abstract class Button {

    private Vector pos;

    public Button(double x, double y) {
        pos = new Vector(x, y);
    }

    abstract void show(GraphicsContext gc);

    abstract void isHovered(GraphicsContext gc);
}
