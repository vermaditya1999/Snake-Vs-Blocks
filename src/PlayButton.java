import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayButton {
    private Vector pos;

    public PlayButton(double x, double y) {
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc) {

        gc.setFill(Color.WHITE);

        double[] xPoints = {pos.x - 50, pos.x - 50, pos.x + 50};
        double[] yPoints = {pos.y - 50, pos.y + 50, pos.y};

        gc.fillPolygon(xPoints, yPoints, 3);
    }

    public boolean isHovered(double mouseX, double mouseY) {

        // TODO: Rectify the logic
        return (Math.abs(mouseX - pos.x) <= 50) && (Math.abs(mouseY - pos.y) <= 50);
    }
}
