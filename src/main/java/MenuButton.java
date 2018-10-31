import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuButton {

    private String text;

    private double posY;

    private double height;
    private double width;

    private boolean hovered;

    {
        height = Game.TILE_SIZE / 2;
        width = 3 * Game.TILE_SIZE;
    }

    public MenuButton(String text, double posY) {
        this.text = text;
        this.posY = posY;
    }

    public void show(GraphicsContext gc) {

        if (hovered) {
            gc.setFill(Color.WHITE);
            gc.fillRect(Game.SCREEN_WIDTH / 2 - width / 2, posY - height / 2, width, height);
        } else {
            gc.setLineWidth(2.0);
            gc.setStroke(Color.WHITE);
            gc.strokeRect(Game.SCREEN_WIDTH / 2 - width / 2, posY - height / 2, width, height);
        }

        gc.setFont(new Font("Consolas", 20));

        if (hovered) {
            gc.setFill(Menu.BG_COLOR);
        } else {
            gc.setFill(Color.WHITE);
        }
        gc.fillText(text, Game.SCREEN_WIDTH / 2, posY);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - Game.SCREEN_WIDTH / 2) <= width / 2 && Math.abs(mouseY - posY) <= height / 2;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}
