import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Wall {

    private static Random random = new Random();

    private Vector pos;
    private double length;

    public Wall(double x, double y) {

        // Here pos is the top coordinate of the wall
        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, y * App.TILE_SIZE);
        length = App.TILE_SIZE;

        // 30% chances of double length wall
        int choose = random.nextInt(3);
        if (choose == 1) {
            length += App.TILE_SIZE;
        }
    }

    public void show(GraphicsContext gc) {

        gc.setLineWidth(4);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(pos.x, pos.y, pos.x, pos.y + length);
    }

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isOver() {
        return pos.y >= App.SCREEN_HEIGHT;
    }
}
