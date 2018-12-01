package com.snakevsblocks.entity;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.LinkedList;

public class Snake implements Serializable {

    private LinkedList<SnakeBall> snakeBalls;

    public Snake() {

        snakeBalls = new LinkedList<SnakeBall>();

        // Add one initial ball
        snakeBalls.add(new SnakeBall(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE));

        // Start with 10 balls
        addBalls(9);
    }

    private double lerp(double x, double y) {
        return x + (y - x) * 0.2;
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
        if (snakeBalls.size() > 0) {
            snakeBalls.removeLast();
        }
    }

    // Prerequisite: Snake has at least one ball
    public void update(double mouseX, double mouseY, LinkedList<Wall> walls, LinkedList<Block> blocks) {

        // Prevent half snake from leaving the screen
        if (mouseX > App.SCREEN_WIDTH - SnakeBall.RADIUS) {
            mouseX = App.SCREEN_WIDTH - SnakeBall.RADIUS;
        }
        if (mouseX < SnakeBall.RADIUS) {
            mouseX = SnakeBall.RADIUS;
        }

        // Set the coordinates of the head to the coordinates of mouse
        Vector head = snakeBalls.getFirst().getPos();
        double prevHeadX = head.x;
        head.x = lerp(head.x, mouseX);

        // Handle collision with walls
        for (Wall w : walls) {
            Vector wPos = w.getPos();
            double diff = head.y - SnakeBall.RADIUS - wPos.y;
            if (diff <= w.getLength() && diff >= -2 * SnakeBall.RADIUS) {
                double left = wPos.x - Wall.WIDTH / 2 - SnakeBall.RADIUS;
                double right = wPos.x + Wall.WIDTH / 2 + SnakeBall.RADIUS;

                if (Math.abs(head.x - wPos.x) <= Wall.WIDTH / 2 + SnakeBall.RADIUS) {
                    if (head.x < wPos.x) {
                        head.x = left;
                    } else {
                        head.x = right;
                    }
                }
                if (prevHeadX <= left && head.x > left) {
                    head.x = left;
                }
                if (prevHeadX >= right && head.x < right) {
                    head.x = right;
                }
            }
        }

        // Handle side collision with Blocks
        for (Block b : blocks) {
            Vector bPos = b.getPos();
            double diff = head.y - SnakeBall.RADIUS - bPos.y + App.TILE_SIZE / 2;

            if (diff <= App.TILE_SIZE && diff >= -2 * SnakeBall.RADIUS) {
                double left = bPos.x - App.TILE_SIZE / 2 - SnakeBall.RADIUS;
                double right = bPos.x + App.TILE_SIZE / 2 + SnakeBall.RADIUS;

                if (prevHeadX <= left && head.x > left) {
                    head.x = left;
                }
                if (prevHeadX >= right && head.x < right) {
                    head.x = right;
                }
            }
        }

        // Prevent snake from going above its line
        if (head.y > App.SCREEN_HEIGHT / 2 + App.TILE_SIZE) {
            double y = Math.max(head.y - 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE);
            snakeBalls.getFirst().setPos(head.x, y);
        }

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
        if (!isDead()) {
            Vector head = snakeBalls.get(0).getPos();
            gc.setFont(Font.CONSOLAS_XSMALL);
            gc.setFill(Color.WHITE);
            gc.fillText(Integer.toString(snakeBalls.size()), head.x, head.y - SnakeBall.RADIUS - 8);

            // Show all the balls
            for (SnakeBall snakeBall : snakeBalls) {
                snakeBall.show(gc);
            }
        }
    }

    public boolean isDead() {
        return snakeBalls.size() == 0;
    }

    // Prerequisite: Snake has at least one ball
    public Vector getHeadVector() {
        return snakeBalls.get(0).getPos();
    }

    public int getLength() {
        return snakeBalls.size();
    }

    // This method must be called after adding a small burst at the snake's head pos
    public void removeHead() {
        snakeBalls.removeFirst();
    }

    public void removeTail() {
        snakeBalls.removeLast();
    }

    public boolean inPos() {
        return snakeBalls.getFirst().getPos().y <= App.SCREEN_HEIGHT / 2 + App.TILE_SIZE;
    }
}