package com.snakevsblocks.entity.burst;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LargeParticle extends Particle {

    public LargeParticle(double x, double y) {
        super(x, y);
        radius = 7;
        vel.mult(1.2);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(color[0], color[1], color[2], alpha));
        gc.fillRect(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
