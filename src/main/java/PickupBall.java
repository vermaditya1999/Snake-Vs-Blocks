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
        gc.setFill(Color.BROWN);
        gc.fillRect(pos.x, pos.y,20,20);

        // Display text
        gc.setFont(new Font("Consolas", 20));
        gc.fillText("PB", pos.x, pos.y + 2*radius);

    }
}
