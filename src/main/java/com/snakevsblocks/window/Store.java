package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.gui.button.BackButton;
import com.snakevsblocks.gui.button.BlackBackButton;
import com.snakevsblocks.gui.button.BuyButton;
import com.snakevsblocks.util.Font;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Store class.
 * This window gives the user the option to choose different skins for the snake, buying them with the coins.
 */
public class Store extends Window {

    /**
     * Path to save the serialized file.
     */
    public static final String PATH = App.PATH + "store.ser";

    /**
     * Background color of the window.
     */
    public static final Color BG_COLOR = Color.rgb(245, 245, 245);

    /**
     * Foreground color of the window.
     */
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    /**
     * Total coins collected from the beginning of the game.
     */
    private int coins;

    /**
     * The current color of the snake.
     */
    private int[] snakeColor;

    /**
     * Backbuttonto go back to Menu.
     */
    private BackButton backButton;
    private BuyButton one;
    private BuyButton two;
    private BuyButton three;
    private BuyButton four;
    private BuyButton five;

    private BuyButton curSelectedbtn;

    /**
     * Creates a new store.
     *
     * @param wc     The window Controller.
     * @param canvas The canvas.
     */
    public Store(WindowController wc, Canvas canvas) {
        super(wc, canvas);

        coins = 0;
        backButton = new BlackBackButton(App.TILE_SIZE / 4 + 10, App.TILE_SIZE / 4);

        one = new BuyButton(App.TILE_SIZE * 2.5, BuyButton.ButtonState.SELECTED, 0, new int[]{150, 204, 208}, "Egg Blue");
        two = new BuyButton(App.TILE_SIZE * 3.5, BuyButton.ButtonState.NA, 10, new int[]{194, 232, 18}, "Bitter Lemon");
        three = new BuyButton(App.TILE_SIZE * 4.5, BuyButton.ButtonState.NA, 20, new int[]{176, 132, 204}, "African Violet");
        four = new BuyButton(App.TILE_SIZE * 5.5, BuyButton.ButtonState.NA, 30, new int[]{237, 180, 88}, "Sunray");
        five = new BuyButton(App.TILE_SIZE * 6.5, BuyButton.ButtonState.NA, 40, new int[]{219, 84, 97}, "Terra Cotta");

        curSelectedbtn = one;
        snakeColor = one.getColor();
        windowController.setSnakeColor(snakeColor);
    }

    /**
     * Refreshes the Buttons.
     * Prerequisites: Exactly one button should have the state selected.
     */
    public void refreshButtons() {
        if (one.getState() == BuyButton.ButtonState.BUY) {
            if (coins < one.getValue()) {
                one.setState(BuyButton.ButtonState.NA);
            }
        } else if (one.getState() == BuyButton.ButtonState.NA) {
            if (coins >= one.getValue()) {
                one.setState(BuyButton.ButtonState.BUY);
            }
        }

        if (two.getState() == BuyButton.ButtonState.BUY) {
            if (coins < two.getValue()) {
                two.setState(BuyButton.ButtonState.NA);
            }
        } else if (two.getState() == BuyButton.ButtonState.NA) {
            if (coins >= two.getValue()) {
                two.setState(BuyButton.ButtonState.BUY);
            }
        }

        if (three.getState() == BuyButton.ButtonState.BUY) {
            if (coins < three.getValue()) {
                three.setState(BuyButton.ButtonState.NA);
            }
        } else if (three.getState() == BuyButton.ButtonState.NA) {
            if (coins >= three.getValue()) {
                three.setState(BuyButton.ButtonState.BUY);
            }
        }

        if (four.getState() == BuyButton.ButtonState.BUY) {
            if (coins < four.getValue()) {
                four.setState(BuyButton.ButtonState.NA);
            }
        } else if (four.getState() == BuyButton.ButtonState.NA) {
            if (coins >= four.getValue()) {
                four.setState(BuyButton.ButtonState.BUY);
            }
        }

        if (five.getState() == BuyButton.ButtonState.BUY) {
            if (coins < five.getValue()) {
                five.setState(BuyButton.ButtonState.NA);
            }
        } else if (five.getState() == BuyButton.ButtonState.NA) {
            if (coins >= five.getValue()) {
                five.setState(BuyButton.ButtonState.BUY);
            }
        }
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.STORE) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.STORE) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (backButton.isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.MENU, mouseX, mouseY);
                } else if (one.isHovered(mouseX, mouseY)) {
                    if (one.getState() == BuyButton.ButtonState.BUY) {
                        coins -= one.getValue();
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        one.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = one.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = one;
                        refreshButtons();
                        serialize();
                    } else if (one.getState() == BuyButton.ButtonState.SELECT) {
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        one.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = one.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = one;
                        serialize();
                    }
                } else if (two.isHovered(mouseX, mouseY)) {
                    if (two.getState() == BuyButton.ButtonState.BUY) {
                        coins -= two.getValue();
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        two.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = two.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = two;
                        refreshButtons();
                        serialize();
                    } else if (two.getState() == BuyButton.ButtonState.SELECT) {
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        two.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = two.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = two;
                        serialize();
                    }
                } else if (three.isHovered(mouseX, mouseY)) {
                    if (three.getState() == BuyButton.ButtonState.BUY) {
                        coins -= three.getValue();
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        three.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = three.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = three;
                        refreshButtons();
                        serialize();
                    } else if (three.getState() == BuyButton.ButtonState.SELECT) {
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        three.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = three.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = three;
                        serialize();
                    }
                } else if (four.isHovered(mouseX, mouseY)) {
                    if (four.getState() == BuyButton.ButtonState.BUY) {
                        coins -= four.getValue();
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        four.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = four.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = four;
                        refreshButtons();
                        serialize();
                    } else if (four.getState() == BuyButton.ButtonState.SELECT) {
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        four.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = four.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = four;
                        serialize();
                    }
                } else if (five.isHovered(mouseX, mouseY)) {
                    if (five.getState() == BuyButton.ButtonState.BUY) {
                        coins -= five.getValue();
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        five.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = five.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = five;
                        refreshButtons();
                        serialize();
                    } else if (five.getState() == BuyButton.ButtonState.SELECT) {
                        curSelectedbtn.setState(BuyButton.ButtonState.SELECT);
                        five.setState(BuyButton.ButtonState.SELECTED);
                        snakeColor = five.getColor();
                        windowController.setSnakeColor(snakeColor);
                        curSelectedbtn = five;
                        serialize();
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.STORE) {
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
        if (backButton.isHovered(mouseX, mouseY) ||
                one.isHovered(mouseX, mouseY) || two.isHovered(mouseX, mouseY) ||
                three.isHovered(mouseX, mouseY) || four.isHovered(mouseX, mouseY) ||
                five.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Store.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Heading
        gc.setFill(Store.FG_COLOR);
        gc.setFont(Font.GOTHAM_MEDIUM);
        gc.fillText("Store", App.SCREEN_WIDTH / 2, App.TILE_SIZE);

        // Show buttons
        backButton.show(gc);

        // Show Buy buttons
        one.show(gc);
        two.show(gc);
        three.show(gc);
        four.show(gc);
        five.show(gc);
    }

    /**
     * Add coins to the store.
     * @param coins The number of coins to be added.
     */
    public void addCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Serialize the window.
     */
    public void serialize() {
        ObjectOutputStream out = null;
        try {
            try {
                out = new ObjectOutputStream(new FileOutputStream(Store.PATH));
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

    /**
     * Get current snake color.
     * @return The current snake color.
     */
    public int[] getSnakeColor() {
        return snakeColor;
    }

    @Override
    public void init(WindowController windowController, Canvas canvas) {
        super.init(windowController, canvas);
        windowController.setSnakeColor(snakeColor);
    }
}
