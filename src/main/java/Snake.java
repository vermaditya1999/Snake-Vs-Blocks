import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Snake {

    private LinkedList<SnakeBall> snake;

    public Snake() {

        snake = new LinkedList<SnakeBall>();

        // Add one initial ball
        snake.add(new SnakeBall(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 + Game.TILE_SIZE));

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

        Vector tail = snake.get(snake.size() - 1).getPos();
        snake.add(new SnakeBall(tail.x, tail.y + 2 * SnakeBall.RADIUS));
    }

    // Prerequisite: Snake has at least one ball
    public void update(double mouseX, double mouseY) {

        // Set the coordinates of the head to the coordinates of mouse
        Vector head = snake.get(0).getPos();
        head.x = mouseX;

        // Update rest of the balls
        for (int i = 1; i < snake.size(); i++) {
            Vector prev = snake.get(i - 1).getPos();
            Vector cur = snake.get(i).getPos();

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
            slant.mult(0.18);

            // Make dir vector to actually point the desired location
            dir.add(slant);
            dir.add(prev);

            cur.set(dir);
        }
    }

    public void show(GraphicsContext gc) {

        // Show all the balls
        for (SnakeBall snakeBall : snake) {
            snakeBall.show(gc);
        }
    }
}