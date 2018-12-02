package com.snakevsblocks.entity.burst;

import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * Abstract Particle class used to create particles for explosion.
 */
public abstract class Particle implements Serializable {

    /**
     * Contains different colors for particles.
     */
    public static final int COLORS[][] = {
            {221, 202, 217},
            {210, 255, 150},
            {241, 232, 184},
            {89, 248, 232},
            {118, 153, 212},
            {99, 180, 209},
            {144, 252, 249}
    };

    /**
     * The position of the particle.
     */
    protected Vector pos;

    /**
     * The velocity of the particle.
     */
    protected Vector vel;

    /**
     * Color of the particle.
     */
    protected int[] color;

    /**
     * Radius of the particle.
     */
    protected double radius;

    /**
     * Alpha value of the particle.
     */
    protected double alpha;

    /**
     * Creates a new Particle.
     *
     * @param x x coordinate of the particle.
     * @param y y coordinate of the particle.
     */
    public Particle(double x, double y) {
        pos = new Vector(x, y);
        vel = Vector.random();
        vel.mult(5);
        alpha = 1;
        color = chooseColor();
    }

    /**
     * Check if particle is dead or not.
     * @return true if particle is dead.
     */
    public boolean isDead() {
        return alpha <= 0 || radius <= 0;
    }

    /**
     * Update the particle.
     */
    public void update() {
        pos.add(vel);
        radius -= 0.25;
        alpha -= 0.025;
    }

    /**
     * Choose a color from the COLORS array for the particle.
     * @return integer array of size three.
     */
    private int[] chooseColor() {
        int choose = Random.nextInt(Particle.COLORS.length);
        return Particle.COLORS[choose];
    }

    /**
     * Show the particle.
     * @param gc Graphics Context on which to show the particle.
     */
    public abstract void show(GraphicsContext gc);
}
