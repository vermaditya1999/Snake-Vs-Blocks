package com.snakevsblocks;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Snake {

    private LinkedList<SnakeBall> snakeBalls;

    public Snake() {

        snakeBalls = new LinkedList<SnakeBall>();

        // Add one initial ball
        snakeBalls.add(new SnakeBall(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE));

        // Start with 10 balls
        addBalls(9);
    }

    // Linear interpolation, reference: https://en.wikipedia.org/wiki/Linear_interpolation
    private double lerp(double v0, double v1, double t) {
        return (1 - t) * v0 + t * v1;
    }

    // Prerequisite: Snake has at least one ball
    public void addBalls(int n) {

        for (int i = 0; i < n; i++) {
            Vector tail = snakeBalls.get(snakeBalls.size() - 1).getPos();
            snakeBalls.add(new SnakeBall(tail.x, tail.y + 2 * SnakeBall.RADIUS));
        }
    }

    // Prerequisite: Snake has at least one ball
    public void removeBall() {
        if (snakeBalls.size() > 1) {
            snakeBalls.removeLast();
        }
    }

    // Prerequisite: Snake has at least one ball
    public void update(double mouseX, double mouseY) {

        // Set the coordinates of the head to the coordinates of mouse
        Vector head = snakeBalls.get(0).getPos();
        head.x = mouseX;

        // Update rest of the balls
        for (int i = 1; i < snakeBalls.size(); i++) {
            Vector prev = snakeBalls.get(i - 1).getPos();
            Vector cur = snakeBalls.get(i).getPos();

            // The direction vector
            // Direction: From prev to cur
            // Magnitude: SnakeBall.RADIUS * 2
            Vector dir = Vector.sub(cur, prev);
            dir.setMag(SnakeBall.RADIUS * 2);

            // The down vector
            // Direction: Downwards
            // Magnitude: SnakeBall.RADIUS * 2
            Vector down = new Vector(0, SnakeBall.RADIUS * 2);

            // The slant vector
            // Direction: From dir to down
            // Magnitude: Experimental hardcoded value
            // Could we make it better by multiplying teh slant according to the distance between
            // prev and cur?
            Vector slant = Vector.sub(down, dir);
            slant.mult(0.2);

            // Make dir vector to actually point the desired location
            dir.add(slant);
            dir.add(prev);

            cur.set(dir);
        }
    }

    public void show(GraphicsContext gc) {

        // Show all the balls
        for (SnakeBall snakeBall : snakeBalls) {
            snakeBall.show(gc);
        }
    }

    // Prerequisite: Snake has at least one ball
    public Vector getHeadVector() {
        return snakeBalls.get(0).getPos();
    }
}