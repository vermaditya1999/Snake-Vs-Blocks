package com.snakevsblocks.entity.burst;

import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public abstract class Particle implements Serializable {

    public static final int COLORS[][] = {
            {221, 202, 217},
            {210, 255, 150},
            {241, 232, 184},
            {89, 248, 232},
            {118, 153, 212},
            {99, 180, 209},
            {144, 252, 249}
    };

    protected Vector pos;
    protected Vector vel;
    protected int[] color;

    protected double radius;
    protected double alpha;

    public Particle(double x, double y) {
        pos = new Vector(x, y);
        vel = Vector.random();
        vel.mult(5);
        alpha = 1;
        color = chooseColor();
    }

    public boolean isDead() {
        return alpha <= 0 || radius <= 0;
    }

    public void update() {
        pos.add(vel);
        radius -= 0.25;
        alpha -= 0.025;
    }

    private int[] chooseColor() {
        int choose = Random.nextInt(Particle.COLORS.length);
        return Particle.COLORS[choose];
    }

    public abstract void show(GraphicsContext gc);
}
