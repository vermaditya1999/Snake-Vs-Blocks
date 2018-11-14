package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.button.BackButton;
import com.snakevsblocks.gui.button.BlackBackButton;
import com.snakevsblocks.util.Font;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Info extends Window {

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    public BackButton backButton;

    public Info(WindowController wc, Canvas canvas) {
        super(wc, canvas);

        backButton = new BlackBackButton(App.TILE_SIZE / 4 + 5, App.TILE_SIZE / 4);
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.INFO) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.INFO) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (backButton.isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.INFO) {
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

        // Heading
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText("About", App.SCREEN_WIDTH / 2, App.TILE_SIZE);

        // Show buttons
        backButton.show(gc);
    }
}
