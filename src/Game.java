import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game {
    public final static int GRID_ROWS = 8;
    public final static int GRID_COLS = 5;
    public final static double TILE_SIZE = 75;

    // boolean variable true only if there is a saved game
    private boolean savedState;

    // Mark this variable transient
    private GraphicsContext gc;

    // Mark this variable transient
    private Menu menu;

    public Game(GraphicsContext graphicsContext) {
        gc = graphicsContext;
        savedState = false;

        menu = new Menu();
    }

    public void showMenu() {
        menu.showMenu();
    }

    private class Menu {
        private double width = Main.SCREEN_WIDTH;
        private double height = Main.SCREEN_HEIGHT;
        private double tile = Game.TILE_SIZE;

        public void showMenu() {

            // set background
            gc.setFill(Color.rgb(32, 32, 32));
            gc.fillRect(0, 0, width, height);

            // display rulers
            gc.strokeLine(width / 2, 0, width / 2, height);
            gc.strokeLine(0, height / 2, width, height / 2);

            gc.setFill(Color.WHITE);

            gc.setFont(new Font(64));
            gc.fillText("Snake", width / 2, height / 2 - 2 * tile);
            gc.fillText("Vs", width / 2, height / 2 - tile - 10);
            gc.fillText("Blocks", width / 2, height / 2 - 20);

            gc.setFont(new Font(32));
            gc.fillText("Click to Play", width / 2, height / 2 + tile);

            // help menu
            gc.fillRect(0, 0, tile, tile);

            // leader board
            gc.setFill(Color.BLANCHEDALMOND);
            gc.fillRect(tile, (GRID_ROWS - 2) * tile, tile, tile);

            // something else, if needed
            gc.setFill(Color.CRIMSON);
            gc.fillRect(2 * tile, (GRID_ROWS - 2) * tile, tile, tile);

            // resume game
            gc.setFill(Color.AQUAMARINE);
            gc.fillRect(3 * tile, (GRID_ROWS - 2) * tile, tile, tile);
        }
    }
}
