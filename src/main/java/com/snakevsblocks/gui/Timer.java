package com.snakevsblocks.gui;

import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

abstract public class Timer {

    public final static double MAX_VAL = 300;  // 5 seconds at 60 FPS

    protected Vector pos;
    protected double val;

    public Timer(double x, double y) {
        pos = new Vector(x, y);
        val = 0;
    }

    public void reset() {
        val = Timer.MAX_VAL;
    }

    // Prerequisite: Call this method before isActive method
    public void update() {
        val--;
    }

    public boolean isActive() {
        return val > 0;
    }

    abstract public void run(GraphicsContext gc);
}
