import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResumeButton {

    private Vector pos;

    private double size;

    {
        pos = new Vector(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
        size = App.TILE_SIZE * 0.75;
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