package com.snakevsblocks.entity.token;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Destroyer extends Token {

    private Image image;

    public Destroyer(double x, double y) {
        super(x, y);

        try {
            image = new Image("img/destroyer.png");
        } catch (Exception ex) {
            System.err.println("Invalid path!");
        }
    }

    @Override
    public void show(GraphicsContext gc) {
        super.show(gc);

        if (image == null) {
            gc.setFill(Color.HOTPINK);
            gc.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.drawImage(image, pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
        }
    }
}
