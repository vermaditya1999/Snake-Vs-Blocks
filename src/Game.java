import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Game {

    public final static int GRID_ROWS = 8;
    public final static int GRID_COLS = 5;

    public final static double TILE_SIZE = 75;

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;

    public Windows currentWindow;

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

        // Start the animation loop
        animationLoop();
    }

    public void setWindow(Windows w) {
        switch (w) {
            case Menu: {
                currentWindow = Windows.Menu;
                menu.bringCanvasToFront();
            }
            break;

            case Gameplay: {
                currentWindow = Windows.Gameplay;
                gameplay.bringCanvasToFront();
            }
            break;

            case Leaderboard: {
                currentWindow = Windows.Leaderboard;
                leaderboard.bringCanvasToFront();
            }
            break;
        }
    }

    private void animationLoop() {

        // Initialize animationTimer
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (currentWindow) {
                    case Menu:
                        menu.show();
                        break;

                    case Gameplay:
                        gameplay.show();
                        break;

                    case Leaderboard:
                        leaderboard.show();
                        break;
                }
            }
        };

        // Start the animationTimer
        animationTimer.start();
    }
}
