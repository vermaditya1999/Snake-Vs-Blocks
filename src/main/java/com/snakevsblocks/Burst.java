package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Burst {

    protected ArrayList<Particle> particles;
    protected Vector origin;
    protected int num;

    public Burst(double x, double y) {
        particles = new ArrayList<Particle>();
        origin = new Vector(x, y);
        num = 0;
    }

    public boolean isOver() {
        return particles.size() == 0;
    }

    public abstract void show(GraphicsContext gc);
}
