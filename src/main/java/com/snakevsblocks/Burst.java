package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Burst {
    protected ArrayList<Particle> particles;
    protected Vector origin;
    protected int maxNum;
    protected int curNum;

    public Burst(double x, double y) {
        particles = new ArrayList<Particle>();
        origin = new Vector(x, y);
        maxNum = 15;
        curNum = 0;

        particles.add(new Particle(x, y));
    }

    public boolean isOver() {
        return particles.size() == 0;
    }

    public abstract void show(GraphicsContext gc);
}
