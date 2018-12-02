package com.snakevsblocks.gui.timer;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.Wall;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

abstract public class Timer implements Serializable {

    public final static double MAX_VAL = 300;  // 5 seconds at 60 FPS
    public final static double BAR_WIDTH = App.TILE_SIZE;
    public final static double BAR_HEIGHT = Wall.WIDTH;

    protected Vector pos;
    protected double val;

    public Timer(double x, double y) {
        pos = new Vector(x, y);
        val = 0;
    }

    public void reset() {
        val = 0;
    }

    public void set() {
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
