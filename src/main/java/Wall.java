import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall {

    private Vector pos;

    private float length;

    public Wall (double x, double y, float length){

        this.length = length;
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc){

        gc.setFill(Color.WHITE);
        gc.setLineWidth(5);
        gc.strokeLine(pos.x, pos.y, pos.x, pos.y + length );
    }
}
