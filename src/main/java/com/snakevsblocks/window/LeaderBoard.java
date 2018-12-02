package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.ScorePane;
import com.snakevsblocks.gui.button.BackButton;
import com.snakevsblocks.gui.button.BlackBackButton;
import com.snakevsblocks.util.Font;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class LeaderBoard extends Window {

    public static final String PATH = App.PATH + "leaderboard.ser";

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);
    private static final int MAX_SIZE = 10;

    private LinkedList<ScorePane> scorePanes;

    private BackButton backButton;

    public LeaderBoard(WindowController wc, Canvas canvas) {
        super(wc, canvas);
        scorePanes = new LinkedList<ScorePane>();

        backButton = new BlackBackButton(App.TILE_SIZE / 4 + 10, App.TILE_SIZE / 4);
    }

    public void addScore(int score) {

        // Get current date
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        boolean added = false;
        for (int i = 0; i < scorePanes.size(); i++) {
            int curScore = scorePanes.get(i).getScore();
            if (score > curScore) {
                scorePanes.add(i, new ScorePane(date, score));
                added = true;
                break;
            }
        }

        if (added) {
            if (scorePanes.size() > MAX_SIZE) {
                scorePanes.removeLast();
            }
        } else {
            if (scorePanes.size() < MAX_SIZE) {
                scorePanes.add(new ScorePane(date, score));
            }
        }
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.LEADERBOARD) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.LEADERBOARD) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (backButton.isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

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

        // Set cursor
        if (backButton.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Leaderboard heading
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText("Leaderboard", App.SCREEN_WIDTH / 2, App.TILE_SIZE);

        // Show message if there is no saved score
        if (scorePanes.size() == 0) {
            gc.setFont(Font.CONSOLAS_SMALL);
            gc.setFill(LeaderBoard.FG_COLOR);
            gc.fillText("No games completed!", App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
        } else {
            // Set Score bars' hovered variable
            for (int rank = 1; rank <= scorePanes.size(); rank++) {
                ScorePane scorePane = scorePanes.get(rank - 1);
                scorePane.setHovered(scorePane.isHovered(mouseX, mouseY));
            }

            // Show Score Bars
            for (int rank = 1; rank <= scorePanes.size(); rank++) {
                scorePanes.get(rank - 1).show(gc, rank);
            }
        }

        // Show buttons
        backButton.show(gc);
    }

    public void serialize() {
        ObjectOutputStream out = null;
        try {
            try {
                out = new ObjectOutputStream(new FileOutputStream(LeaderBoard.PATH));
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