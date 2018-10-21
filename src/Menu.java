import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Menu extends Window {

    public Menu(Game game, Group root) {
        super(game, root);
    }

    @Override
    protected void addEventHandlers() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double x = event.getX();
            double y = event.getY();

            // Leaderboard button
            if ((x >= Game.TILE_SIZE && x <= 2 * Game.TILE_SIZE) &&
                    (y >= (Game.GRID_ROWS - 3) * Game.TILE_SIZE && y <= (Game.GRID_ROWS - 2) * Game.TILE_SIZE)) {
                game.setWindow(Windows.Leaderboard);
            }

            // Play Game button
            if ((x >= 3 * Game.TILE_SIZE && x <= 4 * Game.TILE_SIZE) &&
                    (y >= (Game.GRID_ROWS - 3) * Game.TILE_SIZE && y <= (Game.GRID_ROWS - 2) * Game.TILE_SIZE)) {
                game.setWindow(Windows.Gameplay);
            }
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        gc.setFill(Color.WHITE);

        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 2 * Game.TILE_SIZE);
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE - 10);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 20);

        // Leaderboard button
        gc.fillRect(Game.TILE_SIZE, (Game.GRID_ROWS - 3) * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);

        // Play game button
        gc.fillRect(3 * Game.TILE_SIZE, (Game.GRID_ROWS - 3) * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
    }
}