import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Game implements WindowController {

    public final static int GRID_ROWS = 8;
    public final static int GRID_COLS = 5;

    public final static double TILE_SIZE = 75;

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;

    private Windows currentWindow;

    private Window menu;
    private Window gamePlay;
    private Window leaderBoard;

    public Game(Group root) {

        // Initialize the Windows
        menu = new Menu(this, root);
        gamePlay = new GamePlay(this, root);
        leaderBoard = new LeaderBoard(this, root);

        // Set the starting window to Menu
        setWindow(Windows.Menu);

        // Start the animation loop
        animationLoop();
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

                    case GamePlay:
                        gamePlay.show();
                        break;

                    case LeaderBoard:
                        leaderBoard.show();
                        break;
                }
            }
        };

        // Start the animationTimer
        animationTimer.start();
    }

    @Override
    public void setWindow(Windows w) {
        switch (w) {
            case Menu: {
                currentWindow = Windows.Menu;
                menu.bringCanvasToFront();
            }
            break;

            case GamePlay: {
                currentWindow = Windows.GamePlay;
                gamePlay.bringCanvasToFront();
            }
            break;

            case LeaderBoard: {
                currentWindow = Windows.LeaderBoard;
                leaderBoard.bringCanvasToFront();
            }
            break;
        }
    }
}
