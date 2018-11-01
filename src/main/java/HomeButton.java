import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HomeButton {

    private Vector pos;
    private Image image;
    private double size;

    {
        pos = new Vector(Game.TILE_SIZE / 3, Game.TILE_SIZE / 3);
        image = new Image("home.png");
        size = Game.TILE_SIZE / 3;
    }

    public void show(GraphicsContext gc) {

        gc.drawImage(image, pos.x - size / 2, pos.y - size / 2, size, size);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= size / 2 && Math.abs(mouseY - pos.y) <= size / 2;
    }
}
