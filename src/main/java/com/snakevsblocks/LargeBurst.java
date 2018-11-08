package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;

public class LargeBurst extends Burst {

    public LargeBurst(double x, double y) {
        super(x, y);

        num = 25;

        for (int i = 0; i < num - 10; i++) {
            particles.add(new LargeParticle(x, y));
            num--;
        }
    }

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

