import javafx.scene.Group;

public class Game {

    public final static int GRID_ROWS = 8;
    public final static int GRID_COLS = 5;

    public final static double TILE_SIZE = 75;

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;

    public static Windows currentWindow = Windows.Menu;

    // True only if there's a saved game available
    private boolean savedState;

    private Menu menu;
    private Gameplay gameplay;
    private Leaderboard leaderboard;

    public Game(Group root) {

        // Initialize the Windows
        menu = new Menu(this, root);
        gameplay = new Gameplay(this, root);
        leaderboard = new Leaderboard(this, root);

        // Set the starting window to Menu
        setWindow(Windows.Menu);
    }

    public void showMenu() {
        menu.show();
    }

    public void showGameplay() {
        gameplay.show();
    }

    public void showLeaderboard() {
        leaderboard.show();
    }

    public void setWindow(Windows w) {
        switch (w) {
            case Menu: {
                Main.setTitle("Snake Vs Blocks - Menu");
                currentWindow = Windows.Menu;
                menu.bringCanvasToFront();
            }
            break;

            case Gameplay: {
                Main.setTitle("Snake Vs Blocks");
                currentWindow = Windows.Gameplay;
                gameplay.bringCanvasToFront();
            }
            break;

            case Leaderboard: {
                Main.setTitle("Snake Vs Blocks - Leaderboard");
                currentWindow = Windows.Leaderboard;
                leaderboard.bringCanvasToFront();
            }
            break;
        }
    }
}
