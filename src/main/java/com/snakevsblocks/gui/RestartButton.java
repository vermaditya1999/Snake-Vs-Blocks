package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RestartButton {

    private Vector pos;
    private Image image;
    private double size;

    {
        pos = new Vector(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE * 2.5);
        image = new Image("images/restart.png");
        size = App.TILE_SIZE;
    }

    public void show(GraphicsContext gc) {

        gc.drawImage(image, pos.x - size / 2, pos.y - size / 2, size, size);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= size / 2 && Math.abs(mouseY - pos.y) <= size / 2;
    }
}