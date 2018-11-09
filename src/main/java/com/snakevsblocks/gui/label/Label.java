package com.snakevsblocks.gui.label;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public abstract class Label {

    protected Vector pos;
    protected String text;

    public Label(double x, double y) {
        pos = new Vector(x, y);
    }

    public void update(String text) {
        this.text = text;
    }

    public abstract void show(GraphicsContext gc);
}
