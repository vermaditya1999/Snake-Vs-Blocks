package com.snakevsblocks.entity.burst;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Burst implements Serializable {

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

    public abstract void run(GraphicsContext gc);
}
