package com.snakevsblocks.entity;

import com.snakevsblocks.App;
import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Wall is an entity, which acts as a sideways obstacle to
 * the snake.
 */
public class Wall implements Serializable {

    /**
     * Width of the wall.
     */
    public static final double WIDTH = 6;

    /**
     * Position vector of the top coordinate of the wall.
     */
    private Vector pos;

    /**
     * Length of the wall
     */
    private double length;

    /**
     * Creates a new wall.
     *
     * @param x x coordinate of the top coordinate of the wall
     * @param y y coordinate of the top coordinate of the wall
     */
    public Wall(double x, double y) {

        // Here pos is the top coordinate of the wall
        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, y * App.TILE_SIZE);
        length = App.TILE_SIZE;

        // 33% chances of double length wall
        int choose = Random.nextInt(3);
        if (choose == 1) {
            length += App.TILE_SIZE;
        }
    }

    /**
     * Displays the wall on the screen
     * @param gc Graphic context on which to show the token
     */
    public void show(GraphicsContext gc) {
        gc.setLineWidth(Wall.WIDTH);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(pos.x, pos.y, pos.x, pos.y + length);
    }

    /**
     * Updates speed of the wall to that of game-play.
     * @param speed speed of the game-play.
     */
    public void update(double speed) {
        pos.y += speed;
    }

    /**
     * Checks if the wall has exited the game screen.
     * @return true if wall has exited the screen.
     */
    public boolean isOver() {
        return pos.y >= App.SCREEN_HEIGHT;
    }

    public double getLength() {
        return length;
    }

    public Vector getPos() {
        return pos;
    }
}
