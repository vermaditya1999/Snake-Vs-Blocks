package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

public abstract class Particle {

    private Vector pos;
    private Vector vel;

    private double radius;
    private double alpha;

    public Particle(double x, double y) {
        pos = new Vector(x, y);
        vel = Vector.random();
        vel.mult(5);
        radius = 6;
        alpha = 1;
    }

    public boolean isDead() {
        return alpha <= 0 || radius <= 0;
    }

    public void update() {
        pos.add(vel);
        radius -= 0.25;
        alpha -= 0.025;
    }

    public abstract void show(GraphicsContext gc);
}
