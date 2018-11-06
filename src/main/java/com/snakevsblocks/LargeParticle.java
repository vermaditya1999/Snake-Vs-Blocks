package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LargeParticle extends Particle {

    public LargeParticle(double x, double y) {
        super(x, y);

        radius = 8;
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, alpha));
        gc.fillRect(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
