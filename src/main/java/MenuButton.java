import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class MenuButton {

    private String text;

    private double posY;

    private double height;
    private double width;

    private boolean hovered;

    {
        height = Game.TILE_SIZE / 2;
        width = Game.TILE_SIZE * 3;
    }

    public MenuButton(String text, double posY) {
        this.text = text;
        this.posY = posY;
    }

    public void show(GraphicsContext gc) {

        // Show button
        gc.setLineWidth(2.0);
        gc.setStroke(Menu.FG_COLOR);
        gc.strokeRect(Game.SCREEN_WIDTH / 2 - width / 2, posY - height / 2, width, height);
        if (hovered) {
            gc.setFill(Menu.FG_COLOR);
            gc.fillRect(Game.SCREEN_WIDTH / 2 - width / 2, posY - height / 2, width, height);
        }

        // Set font
        gc.setFont(new Font("Consolas", 20));

        // Show button text
        if (hovered) {
            gc.setFill(Menu.BG_COLOR);
        } else {
            gc.setFill(Menu.FG_COLOR);
        }
        gc.fillText(text, Game.SCREEN_WIDTH / 2, posY);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return hovered = Math.abs(mouseX - Game.SCREEN_WIDTH / 2) <= width / 2 && Math.abs(mouseY - posY) <= height / 2;
    }
}
