import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;

public class BackButton {

    private double[] xPoints;
    private double[] yPoints;

    public BackButton() {

        xPoints = new double[]{35, 15, 22, 22};
        yPoints = new double[]{25, 25, 18, 32};
    }

    public void show(GraphicsContext gc) {

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2.0);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        gc.beginPath();
        gc.moveTo(xPoints[0], yPoints[0]);
        gc.lineTo(xPoints[1], yPoints[1]);
        gc.lineTo(xPoints[2], yPoints[2]);
        gc.moveTo(xPoints[1], yPoints[1]);
        gc.lineTo(xPoints[3], yPoints[3]);
        gc.closePath();

        gc.stroke();
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= xPoints[1] && mouseX <= xPoints[0] && mouseY >= yPoints[2] && mouseY <= yPoints[3];
    }
}
