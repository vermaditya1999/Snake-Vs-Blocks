package com.snakevsblocks;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;

import java.util.EnumMap;

public class App implements WindowController {

    public final static int NUM_ROWS = 8;
    public final static int NUM_COLS = 5;

    // Divisors of 72 : 1,2,3,4,6,8,9,12,18,24,36,72
    public final static double TILE_SIZE = 72;

    public final static double SCREEN_WIDTH = App.TILE_SIZE * App.NUM_COLS;
    public final static double SCREEN_HEIGHT = App.TILE_SIZE * App.NUM_ROWS;

    private static AnimationTimer animationTimer;

    private Windows currentWindow;

    // HashMap from Windows enum constant to Windows object
    private EnumMap<Windows, Window> windows;

    public App(Group root) {

        // Instantiate the windows HashMap
        windows = new EnumMap<Windows, Window>(Windows.class);

        // Initialize the windows and put them in the EnumMap
        initWindows(root);

        // Set the starting window to Menu
        setWindow(Windows.MENU);
    }

    private void initWindows(Group root) {
        windows.put(Windows.MENU, new Menu(this, root));
        windows.put(Windows.GAME, new Game(this, root));
        windows.put(Windows.LEADERBOARD, new LeaderBoard(this, root));
    }

    public void start() {

        if (App.animationTimer == null) {

            // Initialize animationTimer
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    windows.get(currentWindow).show();
                }
            };

            // Start the animationTimer
            animationTimer.start();
        }
    }

    @Override
    public void setWindow(Windows window) {

        // Update the currentWindow field
        currentWindow = window;

        // Bring the window to front
        windows.get(currentWindow).toFront();
    }

    @Override
    public Windows getCurrentWindow() {
        return currentWindow;
    }

    @Override
    public void passEvent(Windows windowEnum, Event event) {
        Window window = windows.get(windowEnum);
        window.fireEvent(event.copyFor(window, window));
    }
}
