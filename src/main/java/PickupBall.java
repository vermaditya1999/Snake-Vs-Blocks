import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PickupBall extends Token{

    public PickupBall(double x, double y){

        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {

        // Display pickup ball
        // To be changed later
        gc.setFill(Color.PAPAYAWHIP);
        gc.fillRect(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);

        // Display text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 15));
        gc.fillText("Ball", pos.x, pos.y + 2.5 * radius);

    }
}
