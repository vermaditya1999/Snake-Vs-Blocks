package com.snakevsblocks;

import com.snakevsblocks.window.*;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

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

    // EnumMap from Windows enum constant to Windows object
    private EnumMap<Windows, Window> windowMap;

    // EnumMap from Windows enum constant to Canvas for each Window
    private EnumMap<Windows, Canvas> canvasMap;

    public App(Group root) {

        // Instantiate the windows EnumMap
        windowMap = new EnumMap<Windows, Window>(Windows.class);

        // Instantiate the canvas EnumMap
        canvasMap = new EnumMap<Windows, Canvas>(Windows.class);

        // Initialize the windows and put them in the EnumMap
        initWindows(root);

        // Set the starting window to Menu
        setWindow(Windows.MENU, 0, 0);
    }

    private void initWindows(Group root) {
        canvasMap.put(Windows.MENU, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
        windowMap.put(Windows.MENU, new Menu(this, canvasMap.get(Windows.MENU)));

        canvasMap.put(Windows.LEADERBOARD, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
        windowMap.put(Windows.LEADERBOARD, new LeaderBoard(this, canvasMap.get(Windows.LEADERBOARD)));

        canvasMap.put(Windows.GAME, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
        windowMap.put(Windows.GAME, new Game(this, canvasMap.get(Windows.GAME)));

        canvasMap.put(Windows.INFO, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
        windowMap.put(Windows.INFO, new Info(this, canvasMap.get(Windows.INFO)));

        root.getChildren().addAll(canvasMap.values());
    }

    public void start() {

        if (App.animationTimer == null) {

            // Initialize animationTimer
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    windowMap.get(currentWindow).show();
                }
            };

            // Start the animationTimer
            animationTimer.start();
        }
    }

    @Override
    public void setWindow(Windows window, double mouseX, double mouseY) {

        // Update the currentWindow field
        currentWindow = window;

        // Bring the window to front
        windowMap.get(currentWindow).toFront();

        // Set mouse variables of window
        windowMap.get(currentWindow).setMouse(mouseX, mouseY);
    }

    @Override
    public Windows getCurrentWindow() {
        return currentWindow;
    }

    @Override
    public void passEvent(Windows windowEnum, Event event) {
        Window window = windowMap.get(windowEnum);
        window.fireEvent(event);
    }

    @Override
    public void addScore(int score) {
        ((Menu) windowMap.get(Windows.MENU)).setPrevScore(score);
        ((LeaderBoard) windowMap.get(Windows.LEADERBOARD)).addScore(score);
    }
}
