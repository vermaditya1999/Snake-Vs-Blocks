import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Destroyer extends Token {

    public Destroyer(double x, double y){

        super(x, y);

    }

    @Override
    public void show(GraphicsContext gc) {

        // Display Destroyer
        // To be changed later
        gc.setFill(Color.ORANGE);
        gc.fillRect(pos.x,pos.y,20,20);

        // Display text
        gc.setFont(new Font("Consolas", 15));
        gc.fillText("Destroy", pos.x, pos.y + 2*radius);

    }
}
