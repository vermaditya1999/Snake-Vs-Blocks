package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Magnet extends Token {

    public Magnet(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getMAGNET() == null) {
            gc.setFill(Color.DARKSLATEBLUE);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getMAGNET(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
