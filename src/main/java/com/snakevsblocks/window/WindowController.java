package com.snakevsblocks.window;

import javafx.event.Event;

/**
 * Window Controller Interface.
 * This Interface has all the methods necessary for communication between different windows.
 */
public interface WindowController {

    /**
     * Change the current window of the application.
     *
     * @param window Window enum constant, denotes the window to which to change
     * @param mouseX x coordinate of the mouse
     * @param mouseY y coordinate of the mouse
     */
    void setWindow(Windows window, double mouseX, double mouseY);

    /**
     * Get the current window in the application.
     * @return enum constant denoting the current window.
     */
    Windows getCurrentWindow();

    /**
     * Pass the event to another window.
     * @param window The destination window.
     * @param event The event to be passed.
     */
    void passEvent(Windows window, Event event);

    /**
     * Add the score and coins in the application.
     * @param score score to be added.
     * @param coins coins to be added.
     */
    void addScore(int score, int coins);

    /**
     * Serialize the state of the game.
     */
    void saveGame();

    /**
     * Load a new game.
     */
    void loadNewGame();

    /**
     * Get the current snake color.
     * @return current snake color in form of 3-sized integer array.
     */
    int[] getSnakeColor();

    /**
     * Set the current snake color.
     * @param color snake color in form of 3-sized integer array.
     */
    void setSnakeColor(int[] color);
}
