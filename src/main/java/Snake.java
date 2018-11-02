import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Snake {

    private LinkedList<SnakeBall> snake;

    public Snake() {

        snake = new LinkedList<SnakeBall>();

        // Add one initial ball
        snake.add(new SnakeBall(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2));

        // Start with 10 balls
        for (int i = 0; i < 9; i++) {
            addBall();
        }
    }

    // Linear interpolation, reference: https://en.wikipedia.org/wiki/Linear_interpolation
    private double lerp(double v0, double v1, double t) {
        return (1 - t) * v0 + t * v1;
    }

    // Prerequisite: Snake has at least one ball
    public void addBall() {

        SnakeBall tail = snake.get(snake.size() - 1);
        snake.add(new SnakeBall(tail.pos.x, tail.pos.y + 2 * SnakeBall.RADIUS));
    }

    // Prerequisite: Snake has at least one ball
    public void update(double mouseX, double mouseY) {

        // Set the coordinates of the head to the coordinates of mouse
        SnakeBall head = snake.get(0);
        head.pos.x = mouseX;

        // Update rest of the balls
        for (int i = 1; i < snake.size(); i++) {
            SnakeBall prev = snake.get(i - 1);
            SnakeBall cur = snake.get(i);

            cur.pos.x = lerp(prev.pos.x, cur.pos.x, 0.5);
        }
    }

    public void show(GraphicsContext gc) {

        // Show all the balls
        for (SnakeBall snakeBall : snake) {
            snakeBall.show(gc);
        }
    }
}