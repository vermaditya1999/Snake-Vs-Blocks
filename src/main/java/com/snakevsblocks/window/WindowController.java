package com.snakevsblocks.window;

import javafx.event.Event;

public interface WindowController {
    void setWindow(Windows window, double mouseX, double mouseY);

    Windows getCurrentWindow();

    void passEvent(Windows window, Event event);

    void addScore(int score, int coins);

    void saveGame();

    void loadNewGame();

    int[] getSnakeColor();
}
