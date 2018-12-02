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

import java.awt.*;

/**
 * The Info Window provides the basic information about the
 * game-play, its objective, and how to play the game.
 */
public class Info extends Window {

    /**
     * Background color of the window.
     */
    public static final Color BG_COLOR = Color.rgb(245, 245, 245);

    /**
     * Foreground color of the window.
     */
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    /**
     * Button used to jump back to the Main menu.
     */
    private BackButton backButton;

    /**
     * Constructs a new Info Window
     *
     * @param wc     TODO
     * @param canvas TODO
     */
    public Info(WindowController wc, Canvas canvas) {
        super(wc, canvas);

        backButton = new BlackBackButton(App.TILE_SIZE / 4 + 10, App.TILE_SIZE / 4);
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

        // List
        gc.setFont(Font.CONSOLAS_XSMALL);
        gc.fillText("Snake-Vs-Blocks is an endless game that\n requires you to guide the snake to reach\n as far as you can.\n\n" +
                "Grab pickup balls to increase your\n snake's length.\n" +
                "Avoid blocks as much as you can.\n Hitting a block decreases the value of\n block and length of your snake by 1.\n" +
                "The following pickups help you breeze\n through the game:\n" +
                "1. Destroy: Destroys all the blocks\n currently on the screen\n" +
                "2. Shield: Protects your snake from\n incoming blocks\n" +
                "3. Magnet: Attracts coins towards\n the snake\n" +
                "4. Special Ball: This rare ball sends\n the snake into ultimate nirvana.\n Play the game to check this out.\n\n" +
                "Collect coins through the game-play.\n Special snake skins can be bought \nusing these coins.", App.SCREEN_WIDTH / 2, 4.5 * App.TILE_SIZE);

        // Show buttons
        backButton.show(gc);
    }
}
