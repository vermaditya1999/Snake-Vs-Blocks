package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;

public class RestartButton extends Button {

    private double size;

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
