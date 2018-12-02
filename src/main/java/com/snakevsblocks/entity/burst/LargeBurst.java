package com.snakevsblocks.entity.burst;

import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;

/**
 * Creates Large bursts.
 *
 * @see Burst
 */
public class LargeBurst extends Burst {

    /**
     * Creates a new large burst.
     * @param x x coordinate of the burst.
     * @param y y coordinate of the burst.
     */
    public LargeBurst(double x, double y) {
        super(x, y);

        num = 25;

        for (int i = 0; i < num - 10; i++) {
            particles.add(new LargeParticle(x, y));
            num--;
        }
    }

    /**
     * Run the burst.
     * @param gc Graphics Context on which to run the burst.
     */
    @Override
    public void run(GraphicsContext gc) {

        if (num > 0) {
            particles.add(new LargeParticle(origin.x, origin.y));
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

