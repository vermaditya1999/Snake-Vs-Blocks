package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;

public class SmallBurst extends Burst {

    public SmallBurst(double x, double y) {
        super(x, y);

        num = 10;
        particles.add(new SmallParticle(x, y));
    }

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

