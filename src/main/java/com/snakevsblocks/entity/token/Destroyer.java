package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Destroyer extends Token {

    public Destroyer(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (Image.getDESTROYER() == null) {
            gc.setFill(Color.HOTPINK);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(Image.getDESTROYER(), pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
