package com.snakevsblocks.entity.burst;

import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;

/**
 * Creates Small Bursts.
 */
public class SmallBurst extends Burst {

    /**
     * Creates a new Small burst.
     *
     * @param x x coordinate of the burst.
     * @param y y coordinate of the burst.
     */
    public SmallBurst(double x, double y) {
        super(x, y);

        num = 10;
        particles.add(new SmallParticle(x, y));
    }

    /**
     * Run the burst.
     * @param gc Graphics Context on which to run the burst.
     */
    @Override
    public void run(GraphicsContext gc) {

        if (num > 0) {
            particles.add(new SmallParticle(origin.x, origin.y));
            num--;
        }

        Iterator it = particles.iterator();
        while (it.hasNext()) {
            Particle particle = (Particle) it.next();
            particle.update();
            if (particle.isDead()) {
                it.remove();
            } else {
                particle.show(gc);
            }
        }
    }
}

