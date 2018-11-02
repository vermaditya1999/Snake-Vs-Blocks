import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeBall {

    private final float radius = 20;
    private int value;

    private Vector pos;

    public SnakeBall(int x, int y) {

        pos = new Vector(x, y);

    }

    public void show(GraphicsContext gc) {

        // Display ball
        gc.setFill(Color.WHITE);
        gc.fillOval(pos.x, pos.y, radius, radius);

        // Display value of ball
        gc.setFont(new Font("Consolas", 20));
        gc.fillText(Integer.toString(value), pos.x, pos.y + 2*radius);

    }

}
