package com.snakevsblocks.gui.button;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public abstract class Button implements Serializable {

    protected Vector pos;

    public Button(double x, double y) {
        pos = new Vector(x, y);
    }

    abstract public void show(GraphicsContext gc);

    abstract public boolean isHovered(double mouseX, double mouseY);
}
