package com.snakevsblocks.entity.burst;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract Burst class used to create explosions.
 */
public abstract class Burst implements Serializable {

    /**
     * Holds the particles in the burst.
     *
     * @see Particle
     */
    protected ArrayList<Particle> particles;

    /**
     * Origin of the burst.
     */
    protected Vector origin;

    /**
     * Number of bursts.
     */
    protected int num;

    /**
     * Creates a new Burst.
     * @param x x coordinate of the burst.
     * @param y y coordinate of the burst.
     */
    public Burst(double x, double y) {
        particles = new ArrayList<Particle>();
        origin = new Vector(x, y);
        num = 0;
    }

    /**
     * Check if burst is over.
     * @return true if burst is over.
     */
    public boolean isOver() {
        return particles.size() == 0;
    }

    /**
     * Run the burst.
     * @param gc Graphics Context on which to run the burst.
     */
    public abstract void run(GraphicsContext gc);
}
