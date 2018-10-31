import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Button {

    private Vector pos;

    public Button(double x, double y) {
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc) {

        gc.setFill(Color.WHITE);
        gc.fillRect(pos.x - Game.TILE_SIZE / 2, pos.y - Game.TILE_SIZE / 2, Game.TILE_SIZE, Game.TILE_SIZE);
    }

    public boolean isHovered(double x, double y) {
        return (Math.abs(x - pos.x) <= Game.TILE_SIZE / 2) && (Math.abs(y - pos.y) <= Game.TILE_SIZE / 2);
    }
}
