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

    protected double mouseX;
    protected double mouseY;

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

    private void loadDefaults() {

        // Enable KeyEvent detection
        canvas.setFocusTraversable(true);

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    protected void resetMouseVars() {
        mouseX = 0;
        mouseY = 0;
    }

    abstract protected void addEventHandlers();

    abstract protected void show();

    protected void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }

    public void bringToFront() {
        canvas.toFront();
    }
}
