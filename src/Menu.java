import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Menu extends Window {

    public Menu(WindowController wc, Group root) {
        super(wc, root);
    }

    @Override
    protected void addEventHandlers() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double x = event.getX();
            double y = event.getY();

            // LeaderBoard button
            if ((x >= Game.TILE_SIZE && x <= 2 * Game.TILE_SIZE) &&
                    (y >= (Game.NUM_ROWS - 3) * Game.TILE_SIZE && y <= (Game.NUM_ROWS - 2) * Game.TILE_SIZE)) {
                windowController.setWindow(Windows.LeaderBoard);
            }

            // Play Game button
            if ((x >= 3 * Game.TILE_SIZE && x <= 4 * Game.TILE_SIZE) &&
                    (y >= (Game.NUM_ROWS - 3) * Game.TILE_SIZE && y <= (Game.NUM_ROWS - 2) * Game.TILE_SIZE)) {
                windowController.setWindow(Windows.GamePlay);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            double x = event.getX();
            double y = event.getY();

            // LeaderBoard button
            if ((x >= Game.TILE_SIZE && x <= 2 * Game.TILE_SIZE) &&
                    (y >= (Game.NUM_ROWS - 3) * Game.TILE_SIZE && y <= (Game.NUM_ROWS - 2) * Game.TILE_SIZE) ||
                (x >= 3 * Game.TILE_SIZE && x <= 4 * Game.TILE_SIZE) &&
                    (y >= (Game.NUM_ROWS - 3) * Game.TILE_SIZE && y <= (Game.NUM_ROWS - 2) * Game.TILE_SIZE)) {
                canvas.setCursor(Cursor.HAND);
            } else {
                canvas.setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Game name
        gc.setFill(Color.WHITE);
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 2 * Game.TILE_SIZE);
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE - 10);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 20);

        // LeaderBoard button
        gc.fillRect(Game.TILE_SIZE, (Game.NUM_ROWS - 3) * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);

        // Play game button
        gc.fillRect(3 * Game.TILE_SIZE, (Game.NUM_ROWS - 3) * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
    }
}
