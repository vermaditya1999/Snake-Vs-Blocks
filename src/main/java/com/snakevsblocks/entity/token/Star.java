package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Star extends Token {

    private int angle;

    public Star(double x, double y) {
        super(x, y);
        angle = 0;
    }

    @Override
    public void show(GraphicsContext gc) {
        angle += 2;
        if (angle > 360) {
            angle = 0;
        }

        gc.translate(pos.x, pos.y);
        gc.rotate(angle);
        if (Image.getSTAR() == null) {
            gc.setFill(Color.HOTPINK);
            gc.fillOval(0 - radius, 0 - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getSTAR(), 0 - radius, 0 - radius, 2 * radius, 2 * radius);
        }
        gc.rotate(-angle);
        gc.translate(-pos.x, -pos.y);
    }
}
