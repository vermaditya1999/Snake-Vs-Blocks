import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayButton {

    private Vector pos;

    private double size = 50;

    public PlayButton(double x, double y) {
        pos = new Vector(x, y);
    }

    public void show(GraphicsContext gc) {

        gc.setFill(Color.WHITE);

        double[] xPoints = {pos.x - size, pos.x - size, pos.x + size};
        double[] yPoints = {pos.y - size, pos.y + size, pos.y};

        gc.fillPolygon(xPoints, yPoints, 3);
    }

    public boolean isHovered(double mouseX, double mouseY) {

        return (mouseY >= pos.y - (size - (mouseX - pos.x)) / 2) &&
                (mouseY <= pos.y + (size - (mouseX - pos.x)) / 2) &&
                (mouseX >= pos.x - size);
    }
}