package com.snakevsblocks.entity.burst;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates Small Particles.
 */
public class SmallParticle extends Particle {

    /**
     * Creates a new Small Particle.
     *
     * @param x x coordinate of the particle.
     * @param y y coordinate of the particle.
     */
    public SmallParticle(double x, double y) {
        super(x, y);
        radius = 6;
    }

    /**
     * Show the Small particle.
     * @param gc Graphics Context on which to show the particle.
     */
    @Override
    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(color[0], color[1], color[2], alpha));
        gc.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
