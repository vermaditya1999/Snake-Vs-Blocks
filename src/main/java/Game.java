import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.event.Event;

import java.util.HashMap;

public class Game implements WindowController {

    public final static int NUM_ROWS = 8;
    public final static int NUM_COLS = 5;

    public final static double TILE_SIZE = 76;

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.NUM_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.NUM_ROWS;

    private Windows currentWindow;

    // HashMap from Windows enum constant to Windows object
    private HashMap<Windows, Window> windows;

    public Game(Group root) {

        // Instantiate the windows HashMap
        windows = new HashMap<Windows, Window>();

        // Put all the instantiated windows in the HashMap
        windows.put(Windows.Menu, new Menu(this, root));
        windows.put(Windows.GamePlay, new GamePlay(this, root));
        windows.put(Windows.LeaderBoard, new LeaderBoard(this, root));

        // Set the starting window to Menu
        setWindow(Windows.Menu);

        // Start the animationLoop
        animationLoop();
    }

    private void animationLoop() {

        // Initialize animationTimer
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                windows.get(currentWindow).show();
            }
        };

        // Start the animationTimer
        animationTimer.start();
    }

    @Override
    public void setWindow(Windows window) {

        // Update the currentWindow field
        currentWindow = window;

        // Bring the window to front
        windows.get(currentWindow).bringToFront();
    }

    @Override
    public Windows currentWindow() {
        return currentWindow;
    }

    @Override
    public void passEvent(Windows window, Event event) {
        windows.get(window).fireEvent(event);
    }
}