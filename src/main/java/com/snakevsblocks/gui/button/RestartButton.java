package com.snakevsblocks.gui.button;

import com.snakevsblocks.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RestartButton extends Button {

    private Image image;
    private double size;

    public RestartButton(double x, double y) {
        super(x, y);
        image = new Image("images/restart.png");
        size = App.TILE_SIZE;
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.drawImage(image, pos.x - size / 2, pos.y - size / 2, size, size);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= size / 2 && Math.abs(mouseY - pos.y) <= size / 2;
    }
}
