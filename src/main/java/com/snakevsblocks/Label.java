package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

public abstract class Label {

    protected Vector pos;
    protected int value;

    public Label(int x, int y) {
        pos = new Vector(x, y);
        value = 0;
    }

    public void update(int value) {
        this.value = value;
    }

    public abstract void show(GraphicsContext gc);
}
