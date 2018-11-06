package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;

public class LargeBurst extends Burst {

    public LargeBurst(double x, double y) {
        super(x, y);

        particles.add(new LargeParticle(x, y));
    }

    @Override
    public void show(GraphicsContext gc) {

        if (curNum < maxNum) {
            particles.add(new LargeParticle(origin.x, origin.y));
            curNum++;
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

