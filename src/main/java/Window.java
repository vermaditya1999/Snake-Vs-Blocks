import javafx.event.Event;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

abstract public class Window {

    protected WindowController windowController;
    protected Canvas canvas;
    protected GraphicsContext gc;

    public Window(WindowController wc, Group root) {

        // Set the windowController
        windowController = wc;

        // Initialize the canvas
        canvas = new Canvas(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Initialize the GraphicsContext
        gc = canvas.getGraphicsContext2D();

        // Add canvas to the root group
        root.getChildren().add(canvas);

        // Load defaults
        loadDefaults();

        // Add Event Handlers
        addEventHandlers();
    }

    protected void loadDefaults() {

        // Enable KeyEvent detection
        canvas.setFocusTraversable(true);

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    public void bringToFront() {
        canvas.toFront();
    }

    abstract protected void addEventHandlers();

    abstract public void show();

    abstract public void fireEvent(Event event);
}
