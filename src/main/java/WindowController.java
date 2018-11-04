import javafx.event.Event;

public interface WindowController {
    void setWindow(Windows window);

    Windows currentWindow();

    void passEvent(Windows window, Event event);
}
