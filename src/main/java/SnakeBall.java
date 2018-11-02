import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBall {

    public static final double RADIUS = 10;

    public Vector pos;

    public SnakeBall(double x, double y) {
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc) {

        // Display ball
        gc.setFill(Color.WHITE);
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);
    }
}
