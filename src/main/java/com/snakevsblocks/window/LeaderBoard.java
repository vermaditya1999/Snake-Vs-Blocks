package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.EntryPane;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class LeaderBoard extends Window {

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);
    private static final int MAX_SIZE = 10;

    private LinkedList<EntryPane> entryPanes;

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);

        entryPanes = new LinkedList<EntryPane>();

        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
        addEntry(Random.nextInt(1000));
    }

    public void addEntry(int score) {

        // Get current date
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        boolean added = false;
        for (int i = 0; i < entryPanes.size(); i++) {
            int curScore = entryPanes.get(i).getScore();
            if (score > curScore) {
                entryPanes.add(i, new EntryPane(date, score));
                added = true;
                break;
            }
        }

        if (added) {
            if (entryPanes.size() > MAX_SIZE) {
                entryPanes.removeLast();
            }
        } else {
            if (entryPanes.size() < MAX_SIZE) {
                entryPanes.add(new EntryPane(date, score));
            }
        }
    }

    @Override
    protected void addEventHandlers() {

        addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.LEADERBOARD) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    windowController.setWindow(Windows.MENU);
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.LEADERBOARD) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    @Override
    public void show() {

        // Set Entry bars' hovered variable
        for (int rank = 1; rank <= entryPanes.size(); rank++) {
            EntryPane entryPane = entryPanes.get(rank - 1);
            entryPane.setHovered(entryPane.isHovered(mouseX, mouseY));
        }

        // Set background
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Leaderboard heading
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText("Leaderboard", App.SCREEN_WIDTH / 2, App.TILE_SIZE);

        // Show Entry Bars
        for (int rank = 1; rank <= entryPanes.size(); rank++) {
            entryPanes.get(rank - 1).show(gc, rank);
        }
    }
}