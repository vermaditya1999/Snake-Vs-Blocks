package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import javafx.scene.canvas.GraphicsContext;

abstract public class BackButton extends Button {

    protected double size;

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
