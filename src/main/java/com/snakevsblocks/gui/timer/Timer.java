package com.snakevsblocks.gui.timer;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.Wall;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * This abstract class has been used to implement timers for different pickups.
 */
abstract public class Timer implements Serializable {

    /**
     * Duration for which the Timer will be on in frames.
     */
    public final static double MAX_VAL = 300;  // 5 seconds at 60 FPS

    /**
     * Width of the timer bar.
     */
    public final static double BAR_WIDTH = App.TILE_SIZE;

    /**
     * Height of the timer bar.
     */
    public final static double BAR_HEIGHT = Wall.WIDTH;

    /**
     * Position vector of the timer.
     */
    protected Vector pos;

    /**
     * Value of the timer in frames.
     */
    protected double val;

    /**
     * Creates a new Timer.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Timer(double x, double y) {
        pos = new Vector(x, y);
        val = 0;
    }

    /**
     * Reset the timer to 0.
     */
    public void reset() {
        val = 0;
    }

    /**
     * Set the timer to full.
     */
    public void set() {
        val = Timer.MAX_VAL;
    }

    /**
     * Update the timer.
     * Prerequisite: Call this method before isActive method
     */
    public void update() {
        val--;
    }

    /**
     * @return true is timer is active.
     */
    public boolean isActive() {
        return val > 0;
    }

    /**
     * Show the timer.
     * @param gc the graphics context on which to show the timer.
     */
    abstract public void run(GraphicsContext gc);
}
