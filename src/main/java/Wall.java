import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall {

    private Vector pos;

    private final double length = Game.TILE_SIZE;

    public Wall (double x, double y){

        pos = new Vector((x - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2, (y - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2);
    }

    public void show(GraphicsContext gc){

        gc.setFill(Color.WHITE);
        gc.setLineWidth(4);
        gc.strokeLine(pos.x, pos.y, pos.x, pos.y + length );
    }

    public void update(double speed) {
        pos.y += speed;
    }
}
