package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SmallParticle extends Particle {

    public SmallParticle(double x, double y) {
        super(x, y);

        radius = 6;
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, alpha));
        gc.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
