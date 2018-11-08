package com.snakevsblocks.window;

import javafx.event.Event;

public interface WindowController {
    void setWindow(Windows window);

    Windows getCurrentWindow();

    void passEvent(Windows window, Event event);
}
