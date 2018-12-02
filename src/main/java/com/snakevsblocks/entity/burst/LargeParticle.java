package com.snakevsblocks.entity.burst;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates Large Particles.
 */
public class LargeParticle extends Particle {

    /**
     * Creates a new Large Particle.
     *
     * @param x x coordinate of the particle.
     * @param y y coordinate of the particle.
     */
    public LargeParticle(double x, double y) {
        super(x, y);
        radius = 7;
        vel.mult(1.2);
    }

    /**
     * Show the particle.
     * @param gc Graphics Context on which to show the particle.
     */
    @Override
    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(color[0], color[1], color[2], alpha));
        gc.fillRect(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
