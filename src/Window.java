import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

abstract public class Window {

    protected Canvas canvas;
    protected GraphicsContext gc;

    public Window() {
        // Initialize the canvas
        canvas = new Canvas(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Initialize the GraphicsContext
        gc = canvas.getGraphicsContext2D();

        // Enable KeyEvent detection
        canvas.setFocusTraversable(true);

        // Load defaults
        loadDefaults();

        // Add Event Handlers
        addEventHandlers();
    }

    private void loadDefaults() {

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    abstract protected void addEventHandlers();

    abstract public void show();

    public void bringCanvasToFront() {
        canvas.toFront();
    }
}
