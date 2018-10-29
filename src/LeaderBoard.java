import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class LeaderBoard extends Window {

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);
    }

    @Override
    protected void addEventHandlers() {
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                windowController.setWindow(Windows.Menu);
            }
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Temporary text
        gc.setFill(Color.WHITE);
        gc.fillText("LeaderBoard", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2);
    }

    @Override
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }
}