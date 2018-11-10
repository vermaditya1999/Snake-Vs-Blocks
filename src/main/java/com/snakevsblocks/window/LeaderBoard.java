package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.ScorePane;
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

    private LinkedList<ScorePane> scorePanes;

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);

        scorePanes = new LinkedList<ScorePane>();

        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
        addScore(Random.nextInt(1000));
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

        addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.LEADERBOARD) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
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

        // Set background
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Leaderboard heading
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText("Leaderboard", App.SCREEN_WIDTH / 2, App.TILE_SIZE);

        // Show message if there is no saved score
        if (scorePanes.size() == 0) {
            gc.setFont(Font.GOTHAM_SMALL);
            gc.setFill(LeaderBoard.FG_COLOR);
            gc.fillText("No games played :(", App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
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
    }
}