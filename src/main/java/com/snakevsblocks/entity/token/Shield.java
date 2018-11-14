package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shield extends Token {

    public Shield(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getSHIELD() == null) {
            gc.setFill(Color.CORNSILK);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getSHIELD(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
