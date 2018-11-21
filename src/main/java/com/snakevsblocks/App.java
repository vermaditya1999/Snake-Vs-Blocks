package com.snakevsblocks;

import com.snakevsblocks.window.*;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.EnumMap;

public class App implements WindowController {

    public final static String PATH = System.getProperty("user.dir") + File.separator + ".save" + File.separator;

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

        // Create the save directory, if not already created
        File saveDir = new File(App.PATH);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

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
        deserializeWindows();

        // Check if the windows have already been deserialized, then only initialize new ones
        if (!windowMap.containsKey(Windows.MENU)) {
            canvasMap.put(Windows.MENU, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
            windowMap.put(Windows.MENU, new Menu(this, canvasMap.get(Windows.MENU)));
        }

        if (!windowMap.containsKey(Windows.GAME)) {
            canvasMap.put(Windows.GAME, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
            windowMap.put(Windows.GAME, new Game(this, canvasMap.get(Windows.GAME)));
        }

        if (!windowMap.containsKey(Windows.LEADERBOARD)) {
            canvasMap.put(Windows.LEADERBOARD, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
            windowMap.put(Windows.LEADERBOARD, new LeaderBoard(this, canvasMap.get(Windows.LEADERBOARD)));
        }

        if (!windowMap.containsKey(Windows.INFO)) {
            canvasMap.put(Windows.INFO, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
            windowMap.put(Windows.INFO, new Info(this, canvasMap.get(Windows.INFO)));
        }

        // Add all the canvas to the root group of the JavaFx scene
        root.getChildren().addAll(canvasMap.values());
    }

    private void deserializeWindows() {
        if ((new File(LeaderBoard.PATH)).exists()) {
            try {
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(new FileInputStream(LeaderBoard.PATH));
                    LeaderBoard leaderBoard = (LeaderBoard) in.readObject();

                    canvasMap.put(Windows.LEADERBOARD, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
                    leaderBoard.init(this, canvasMap.get(Windows.LEADERBOARD));

                    windowMap.put(Windows.LEADERBOARD, leaderBoard);
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if ((new File(Menu.PATH)).exists()) {
            try {
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(new FileInputStream(Menu.PATH));
                    Menu menu = (Menu) in.readObject();

                    canvasMap.put(Windows.MENU, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
                    menu.init(this, canvasMap.get(Windows.MENU));

                    windowMap.put(Windows.MENU, menu);
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if ((new File(Game.PATH)).exists()) {
            try {
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(new FileInputStream(Game.PATH));
                    Game game = (Game) in.readObject();

                    canvasMap.put(Windows.GAME, new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT));
                    game.init(this, canvasMap.get(Windows.GAME));

                    windowMap.put(Windows.GAME, game);
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
        Menu menu = (Menu) windowMap.get(Windows.MENU);
        LeaderBoard leaderBoard = (LeaderBoard) windowMap.get(Windows.LEADERBOARD);

        // Set previous score in Menu
        menu.setPrevScore(score);

        // Remove Resume button
        menu.setSavedGame(false);

        // Reinitialize the Menu buttons
        menu.initMenuButtons();

        // Serialize Menu
        menu.serialize();

        // Add score to Leader-Board
        leaderBoard.addScore(score);

        // Serialize Leader Board
        leaderBoard.serialize();
    }

    @Override
    public void saveGame() {
        Game game = (Game) windowMap.get(Windows.GAME);
        Menu menu = (Menu) windowMap.get(Windows.MENU);

        // Serialize Game
        game.serialize();

        // Enable Resume button
        menu.setSavedGame(true);

        // Reinitialize the Menu buttons
        menu.initMenuButtons();

        // Serialize Menu
        menu.serialize();
    }

    @Override
    public void loadNewGame() {
        ((Game) windowMap.get(Windows.GAME)).loadNewGame();
    }
}
