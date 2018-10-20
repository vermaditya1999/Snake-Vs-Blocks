import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Gameplay extends Window {

    // Temporary field for demonstration
    ArrayList<Burst> bursts = new ArrayList<Burst>();

    public Gameplay(Game game, Group root) {
        super(game, root);
    }

    @Override
    protected void addEventHandlers() {
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                game.setWindow(Windows.Menu);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double x = event.getX();
            double y = event.getY();

            bursts.add(new Burst(x, y));
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Temporary text
        gc.setFill(Color.WHITE);

        gc.fillText("Gameplay", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);

        for (int i = bursts.size() - 1; i >= 0; i--) {
            Burst b = bursts.get(i);

            if (b.isOver()) {
                bursts.remove(i);
            } else {
                b.show(gc);
            }
        }
    }
}
