import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class InfoButton {

    private Vector pos;
    private double radius;
    private boolean hovered;

    {
        pos = new Vector(App.TILE_SIZE / 3, App.TILE_SIZE / 3);
        radius = App.TILE_SIZE / 6;
    }

    public void show(GraphicsContext gc) {

        // Draw circle
        gc.setLineWidth(2.0);
        gc.setStroke(Menu.FG_COLOR);
        gc.strokeOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
        if (hovered) {
            gc.setFill(Menu.FG_COLOR);
            gc.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
        }

        // Show button text
        if (hovered) {
            gc.setFill(Menu.BG_COLOR);
        } else {
            gc.setFill(Menu.FG_COLOR);
        }
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("i", pos.x, pos.y + 1);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return hovered = Math.abs(mouseX - pos.x) <= radius && Math.abs(mouseY - pos.y) <= radius;
    }
}
