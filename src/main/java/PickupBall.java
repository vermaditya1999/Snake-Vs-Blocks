import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PickupBall extends Token {

    private int value;

    public PickupBall(double x, double y) {

        super(x, y);
        assignValue();
    }

    private void assignValue() {
        /* Probability chart for Ball value:
         * 1 : 60%
         * 2 : 30%
         * 3 : 5%
         * 4 : 3%
         * 5 : 2%
         */
        int choose = Random.nextInt(100);
        if (choose < 60) {
            value = 1;
        } else if (choose < 90) {
            value = 2;
        } else if (choose < 95) {
            value = 3;
        } else if (choose < 98) {
            value = 4;
        } else {
            value = 5;
        }

    }

    @Override
    public void show(GraphicsContext gc) {

        // Display pickup ball
        // To be changed later
        gc.setFill(Color.PAPAYAWHIP);
        gc.fillRect(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);

        // Display text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 15));
        gc.fillText(Integer.toString(value), pos.x, pos.y + 2.5 * Token.RADIUS);

    }
}
