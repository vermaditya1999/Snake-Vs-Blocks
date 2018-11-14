package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.button.InfoButton;
import com.snakevsblocks.gui.button.MenuButton;
import com.snakevsblocks.util.Font;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

public class Menu extends Window {

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    private enum MenuButtons {
        RESUME_GAME,
        START_GAME,
        LEADERBOARD,
        EXIT
    }

    // ArrayList to hold all menu buttons
    private EnumMap<MenuButtons, MenuButton> menuButtons;

    // Boolean variable true if a saved game is available
    private boolean savedGame;

    // Score of the previous score
    private int prevScore;

    // Info button
    private InfoButton infoButton;

    public Menu(WindowController wc, Canvas canvas) {
        super(wc, canvas);

        // Set temporary value for demonstration
        prevScore = 0;

        // Set temporary value for demonstration
        savedGame = false;

        // Initialize the menuButtons HashMap
        menuButtons = new EnumMap<MenuButtons, MenuButton>(MenuButtons.class);

        // Add menu buttons
        initMenuButtons();

        // Add info button
        infoButton = new InfoButton(App.TILE_SIZE / 3, App.TILE_SIZE / 3);
    }

    public void initMenuButtons() {

        menuButtons.clear();

        double gap = 0.75;
        double offset = gap * App.TILE_SIZE;

        // Add resume button only if there is a saved game
        if (savedGame) {
            menuButtons.put(MenuButtons.RESUME_GAME, new MenuButton("Resume Game", App.SCREEN_HEIGHT / 2 + gap * App.TILE_SIZE));
        } else {
            offset = 0;  // No Resume App button, set offset to 0
        }

        // Add rest of the buttons
        menuButtons.put(MenuButtons.START_GAME, new MenuButton("Start Game", App.SCREEN_HEIGHT / 2 + gap * App.TILE_SIZE + offset));
        menuButtons.put(MenuButtons.LEADERBOARD, new MenuButton("Leaderboard", App.SCREEN_HEIGHT / 2 + 2 * gap * App.TILE_SIZE + offset));
        menuButtons.put(MenuButtons.EXIT, new MenuButton("Exit", App.SCREEN_HEIGHT / 2 + 3 * gap * App.TILE_SIZE + offset));
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();

            if (currentWindow != Windows.MENU) {
                windowController.passEvent(currentWindow, event);
            } else {

                if (menuButtons.get(MenuButtons.LEADERBOARD).isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.LEADERBOARD, mouseX, mouseY);
                }

                if (menuButtons.get(MenuButtons.START_GAME).isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.GAME, mouseX, mouseY);
                }

                if (menuButtons.get(MenuButtons.EXIT).isHovered(mouseX, mouseY)) {
                    Platform.exit();
                }

                if (infoButton.isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.INFO, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.MENU) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    @Override
    public void show() {

        // Set hovered variable
        boolean hovered = false;
        Iterator entries = menuButtons.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            MenuButton menuButton = (MenuButton) entry.getValue();
            if (menuButton.isHovered(mouseX, mouseY)) {
                hovered = true;
            }
        }

        if (infoButton.isHovered(mouseX, mouseY)) {
            hovered = true;
        }

        // Set cursor
        if (hovered) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Menu.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Show game title
        gc.setFont(Font.GOTHAM_LARGE);
        gc.setFill(Menu.FG_COLOR);
        gc.fillText("Snake", App.SCREEN_WIDTH / 2, App.TILE_SIZE);
        gc.fillText("vs", App.SCREEN_WIDTH / 2, App.TILE_SIZE * 1.75);
        gc.fillText("Blocks", App.SCREEN_WIDTH / 2, App.TILE_SIZE * 2.5);

        // Show previous score
        gc.setFont(Font.MUSEO);
        gc.setFill(Menu.FG_COLOR);
        gc.fillText(Integer.toString(prevScore), App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE / 3);

        // Show the horizontal rule, how to change its length with width of the prevScore?
        gc.setLineWidth(2.0);
        gc.setStroke(Menu.FG_COLOR);
        gc.beginPath();
        gc.moveTo(0, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE / 3);
        gc.lineTo(App.TILE_SIZE * 1.5, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE / 3);
        gc.moveTo(App.SCREEN_WIDTH, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE / 3);
        gc.lineTo(App.SCREEN_WIDTH - App.TILE_SIZE * 1.5, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE / 3);
        gc.stroke();
        gc.closePath();

        // Show Menu buttons
        entries = menuButtons.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            MenuButton menuButton = (MenuButton) entry.getValue();
            menuButton.show(gc);
        }

        // Show Info button
        infoButton.show(gc);
    }

    public void setPrevScore(int prevScore) {
        this.prevScore = prevScore;
    }

    public void setSavedGame(boolean savedGame) {
        this.savedGame = savedGame;
    }

    public void serialize() {
        ObjectOutputStream out = null;
        try {
            try {
                out = new ObjectOutputStream(new FileOutputStream("menu.ser"));
                out.writeObject(this);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}